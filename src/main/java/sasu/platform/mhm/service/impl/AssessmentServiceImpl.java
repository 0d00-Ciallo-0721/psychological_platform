package sasu.platform.mhm.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import sasu.platform.mhm.mapper.AssessmentMapper;
import sasu.platform.mhm.mapper.PreAssessmentTemplateMapper;
import sasu.platform.mhm.pojo.VO.AssessmentDetailVO;
import sasu.platform.mhm.pojo.VO.AssessmentPlanVO;
import sasu.platform.mhm.pojo.VO.AssessmentQuestionVO;
import sasu.platform.mhm.pojo.VO.AssessmentResultVO;
import sasu.platform.mhm.pojo.VO.AssessmentStartVO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.AssessmentListQueryDTO;
import sasu.platform.mhm.pojo.dto.AssessmentPlanCreateDTO;
import sasu.platform.mhm.pojo.dto.AssessmentStartDTO;
import sasu.platform.mhm.pojo.dto.AssessmentSubmitDTO;
import sasu.platform.mhm.pojo.entity.AssessmentPlan;
import sasu.platform.mhm.pojo.entity.AssessmentRecord;
import sasu.platform.mhm.pojo.entity.PreAssessmentQuestion;
import sasu.platform.mhm.pojo.entity.PreAssessmentTemplate;
import sasu.platform.mhm.pojo.entity.UserDetailsImpl;
import sasu.platform.mhm.service.AssessmentService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    private AssessmentMapper assessmentMapper;

    @Autowired
    private PreAssessmentTemplateMapper preAssessmentTemplateMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String currentUserId() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetailsImpl) {
                return ((UserDetailsImpl) principal).getUser().getUserId();
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    @Override
    public PageResult listAssessments(AssessmentListQueryDTO query) {
        String userId = currentUserId();
        if (!StringUtils.hasText(userId)) {
            throw new RuntimeException("未登录");
        }
        if (query == null) {
            query = new AssessmentListQueryDTO();
        }
        int page = query.getPage() == null || query.getPage() <= 0 ? 1 : query.getPage();
        int pageSize = query.getPageSize() == null || query.getPageSize() <= 0 ? 10 : query.getPageSize();

        Map<String, Object> params = new HashMap<>();
        params.put("status", query.getStatus() == null ? 1 : query.getStatus());
        params.put("keyword", query.getKeyword());

        List<AssessmentPlan> allPlans = assessmentMapper.listPlans(params);
        long total = allPlans.size();
        int fromIndex = Math.min((page - 1) * pageSize, allPlans.size());
        int toIndex = Math.min(fromIndex + pageSize, allPlans.size());
        List<AssessmentPlan> pageList = fromIndex < toIndex ? allPlans.subList(fromIndex, toIndex) : Collections.emptyList();

        List<AssessmentPlanVO> voList = new ArrayList<>();
        for (AssessmentPlan plan : pageList) {
            AssessmentPlanVO vo = toPlanVO(plan);
            Integer count = assessmentMapper.countSubmittedByUserAndPlan(userId, plan.getPlanId());
            vo.setMyAttemptCount(count == null ? 0 : count);
            voList.add(vo);
        }
        return new PageResult(total, voList);
    }

    @Override
    public AssessmentDetailVO detail(String planId) {
        if (!StringUtils.hasText(planId)) {
            throw new RuntimeException("planId不能为空");
        }
        AssessmentPlan plan = assessmentMapper.getPlanById(planId);
        if (plan == null) {
            throw new RuntimeException("测评方案不存在");
        }
        PreAssessmentTemplate template = preAssessmentTemplateMapper.getTemplateById(plan.getTemplateId());
        if (template == null) {
            throw new RuntimeException("量表模板不存在");
        }
        List<PreAssessmentQuestion> questions = preAssessmentTemplateMapper.listQuestionsByTemplateId(plan.getTemplateId());

        AssessmentPlanVO planVO = toPlanVO(plan);
        if (template != null) {
            planVO.setScaleType(template.getScaleType());
            planVO.setScaleName(template.getScaleName());
            planVO.setDescription(template.getDescription());
        }

        AssessmentDetailVO vo = new AssessmentDetailVO();
        vo.setPlan(planVO);
        vo.setQuestionCount(questions == null ? 0 : questions.size());
        return vo;
    }

    @Override
    public AssessmentStartVO start(AssessmentStartDTO dto) {
        if (dto == null || !StringUtils.hasText(dto.getPlanId())) {
            throw new RuntimeException("planId不能为空");
        }
        String userId = currentUserId();
        if (!StringUtils.hasText(userId)) {
            throw new RuntimeException("未登录");
        }

        AssessmentPlan plan = assessmentMapper.getPlanById(dto.getPlanId());
        if (plan == null || plan.getStatus() == null || plan.getStatus() != 1) {
            throw new RuntimeException("测评方案不可用");
        }
        LocalDateTime now = LocalDateTime.now();
        if (plan.getStartTime() != null && now.isBefore(plan.getStartTime())) {
            throw new RuntimeException("测评尚未开始");
        }
        if (plan.getEndTime() != null && now.isAfter(plan.getEndTime())) {
            throw new RuntimeException("测评已结束");
        }

        Integer count = assessmentMapper.countSubmittedByUserAndPlan(userId, plan.getPlanId());
        int maxAttempts = plan.getMaxAttempts() == null || plan.getMaxAttempts() <= 0 ? 1 : plan.getMaxAttempts();
        if (count != null && count >= maxAttempts) {
            throw new RuntimeException("已达到最大测评次数");
        }

        String recordId = UUID.randomUUID().toString();
        AssessmentRecord record = AssessmentRecord.builder()
                .recordId(recordId)
                .planId(plan.getPlanId())
                .templateId(plan.getTemplateId())
                .userId(userId)
                .status(0)
                .startedAt(now)
                .build();
        boolean ok = assessmentMapper.insertRecord(record);
        if (!ok) {
            throw new RuntimeException("创建测评记录失败");
        }

        PreAssessmentTemplate template = preAssessmentTemplateMapper.getTemplateById(plan.getTemplateId());
        List<PreAssessmentQuestion> questions = preAssessmentTemplateMapper.listQuestionsByTemplateId(plan.getTemplateId());

        AssessmentPlanVO planVO = toPlanVO(plan);
        if (template != null) {
            planVO.setScaleType(template.getScaleType());
            planVO.setScaleName(template.getScaleName());
            planVO.setDescription(template.getDescription());
        }

        List<AssessmentQuestionVO> questionVOS = toQuestionVOList(questions);

        AssessmentStartVO vo = new AssessmentStartVO();
        vo.setRecordId(recordId);
        vo.setPlan(planVO);
        vo.setQuestions(questionVOS);
        return vo;
    }

    @Override
    public Map<String, Object> submit(AssessmentSubmitDTO dto) {
        if (dto == null || !StringUtils.hasText(dto.getRecordId())) {
            throw new RuntimeException("recordId不能为空");
        }
        String userId = currentUserId();
        if (!StringUtils.hasText(userId)) {
            throw new RuntimeException("未登录");
        }
        AssessmentRecord record = assessmentMapper.getRecordById(dto.getRecordId());
        if (record == null) {
            throw new RuntimeException("测评记录不存在");
        }
        if (!userId.equals(record.getUserId())) {
            throw new RuntimeException("无权提交该测评");
        }
        if (record.getStatus() != null && record.getStatus() == 1) {
            throw new RuntimeException("该测评已提交");
        }

        List<PreAssessmentQuestion> questions = preAssessmentTemplateMapper.listQuestionsByTemplateId(record.getTemplateId());
        if (CollectionUtils.isEmpty(questions)) {
            throw new RuntimeException("量表题目不存在");
        }

        Map<String, Object> answerMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(dto.getAnswers())) {
            for (AssessmentSubmitDTO.AnswerItem a : dto.getAnswers()) {
                if (a != null && StringUtils.hasText(a.getQuestionId())) {
                    answerMap.put(a.getQuestionId(), a.getAnswer());
                }
            }
        }

        int totalScore = 0;
        for (PreAssessmentQuestion q : questions) {
            Object ans = answerMap.get(q.getQuestionId());
            if (q.getRequiredFlag() != null && q.getRequiredFlag() == 1 && ans == null) {
                throw new RuntimeException("必答题未作答: " + q.getQuestionNo());
            }
            int score = calculateScore(q, ans);
            totalScore += score;
        }

        String interpretation = simpleInterpretation(totalScore);

        LocalDateTime now = LocalDateTime.now();
        record.setStatus(1);
        record.setSubmittedAt(now);
        record.setAnswersJson(toJson(answerMap));
        record.setTotalScore(totalScore);
        record.setScoreInterpretation(interpretation);

        boolean ok = assessmentMapper.updateRecord(record);
        if (!ok) {
            throw new RuntimeException("提交测评失败");
        }
        Map<String, Object> res = new HashMap<>();
        res.put("recordId", record.getRecordId());
        res.put("submitted", true);
        res.put("totalScore", totalScore);
        res.put("scoreInterpretation", interpretation);
        return res;
    }

    @Override
    public AssessmentResultVO result(String recordId) {
        if (!StringUtils.hasText(recordId)) {
            throw new RuntimeException("recordId不能为空");
        }
        String userId = currentUserId();
        if (!StringUtils.hasText(userId)) {
            throw new RuntimeException("未登录");
        }
        AssessmentRecord record = assessmentMapper.getRecordById(recordId);
        if (record == null) {
            throw new RuntimeException("测评记录不存在");
        }
        if (!userId.equals(record.getUserId())) {
            throw new RuntimeException("无权查看该测评结果");
        }
        AssessmentPlan plan = assessmentMapper.getPlanById(record.getPlanId());
        PreAssessmentTemplate template = preAssessmentTemplateMapper.getTemplateById(record.getTemplateId());

        AssessmentResultVO vo = new AssessmentResultVO();
        vo.setRecordId(record.getRecordId());
        vo.setPlanId(record.getPlanId());
        if (plan != null) {
            vo.setPlanName(plan.getPlanName());
        }
        if (template != null) {
            vo.setScaleType(template.getScaleType());
            vo.setScaleName(template.getScaleName());
        }
        vo.setTotalScore(record.getTotalScore());
        vo.setScoreInterpretation(record.getScoreInterpretation());
        vo.setRiskLevel(record.getRiskLevel());
        vo.setSubmittedAt(record.getSubmittedAt());
        if (StringUtils.hasText(record.getDimensionScoreJson())) {
            try {
                Map<String, Object> dim = objectMapper.readValue(record.getDimensionScoreJson(),
                        new TypeReference<Map<String, Object>>() {
                        });
                vo.setDimensionScores(dim);
            } catch (Exception e) {
                log.warn("解析维度分失败: {}", e.getMessage());
            }
        }
        return vo;
    }

    @Override
    public PageResult my(Integer page, Integer pageSize) {
        String userId = currentUserId();
        if (!StringUtils.hasText(userId)) {
            throw new RuntimeException("未登录");
        }
        int p = page == null || page <= 0 ? 1 : page;
        int size = pageSize == null || pageSize <= 0 ? 10 : pageSize;

        List<AssessmentRecord> records = assessmentMapper.listRecordsByUser(userId);
        long total = records.size();
        int fromIndex = Math.min((p - 1) * size, records.size());
        int toIndex = Math.min(fromIndex + size, records.size());
        List<AssessmentRecord> pageList = fromIndex < toIndex ? records.subList(fromIndex, toIndex) : Collections.emptyList();

        Map<String, AssessmentPlan> planMap = Collections.emptyMap();
        Map<String, PreAssessmentTemplate> templateMap = Collections.emptyMap();
        if (!pageList.isEmpty()) {
            Set<String> planIds = pageList.stream().map(AssessmentRecord::getPlanId).collect(Collectors.toSet());
            Set<String> templateIds = pageList.stream().map(AssessmentRecord::getTemplateId).collect(Collectors.toSet());
            planMap = new HashMap<>();
            templateMap = new HashMap<>();
            for (String pid : planIds) {
                AssessmentPlan pPlan = assessmentMapper.getPlanById(pid);
                if (pPlan != null) {
                    planMap.put(pid, pPlan);
                }
            }
            for (String tid : templateIds) {
                PreAssessmentTemplate t = preAssessmentTemplateMapper.getTemplateById(tid);
                if (t != null) {
                    templateMap.put(tid, t);
                }
            }
        }

        List<AssessmentResultVO> voList = new ArrayList<>();
        for (AssessmentRecord r : pageList) {
            AssessmentPlan plan = planMap.get(r.getPlanId());
            PreAssessmentTemplate template = templateMap.get(r.getTemplateId());
            AssessmentResultVO vo = new AssessmentResultVO();
            vo.setRecordId(r.getRecordId());
            vo.setPlanId(r.getPlanId());
            if (plan != null) {
                vo.setPlanName(plan.getPlanName());
            }
            if (template != null) {
                vo.setScaleType(template.getScaleType());
                vo.setScaleName(template.getScaleName());
            }
            vo.setTotalScore(r.getTotalScore());
            vo.setScoreInterpretation(r.getScoreInterpretation());
            vo.setRiskLevel(r.getRiskLevel());
            vo.setSubmittedAt(r.getSubmittedAt());
            voList.add(vo);
        }
        return new PageResult(total, voList);
    }

    @Override
    public Map<String, Object> savePlan(AssessmentPlanCreateDTO dto) {
        if (dto == null) {
            throw new RuntimeException("参数不能为空");
        }
        if (!StringUtils.hasText(dto.getPlanName())) {
            throw new RuntimeException("planName不能为空");
        }
        if (!StringUtils.hasText(dto.getTemplateId())) {
            throw new RuntimeException("templateId不能为空");
        }
        PreAssessmentTemplate template = preAssessmentTemplateMapper.getTemplateById(dto.getTemplateId());
        if (template == null) {
            throw new RuntimeException("量表模板不存在");
        }

        String userId = currentUserId();
        boolean isUpdate = StringUtils.hasText(dto.getPlanId());
        String planId = isUpdate ? dto.getPlanId() : UUID.randomUUID().toString();

        AssessmentPlan plan = AssessmentPlan.builder()
                .planId(planId)
                .planName(dto.getPlanName())
                .templateId(dto.getTemplateId())
                .targetRole(dto.getTargetRole())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .maxAttempts(dto.getMaxAttempts() == null || dto.getMaxAttempts() <= 0 ? 1 : dto.getMaxAttempts())
                .status(dto.getStatus() == null ? 1 : dto.getStatus())
                .createdBy(userId)
                .build();

        boolean ok;
        if (isUpdate) {
            ok = assessmentMapper.updatePlan(plan);
        } else {
            ok = assessmentMapper.insertPlan(plan);
        }
        if (!ok) {
            throw new RuntimeException("保存方案失败");
        }
        Map<String, Object> res = new HashMap<>();
        res.put("planId", planId);
        res.put("saved", true);
        return res;
    }

    @Override
    public List<AssessmentResultVO> exportByPlan(String planId) {
        if (!StringUtils.hasText(planId)) {
            throw new RuntimeException("planId不能为空");
        }
        String userId = currentUserId();
        if (!StringUtils.hasText(userId)) {
            throw new RuntimeException("未登录");
        }
        // 简单实现：导出该方案下所有已提交的记录
        List<AssessmentRecord> records = assessmentMapper.listRecordsByUser(null);
        if (records == null) {
            return Collections.emptyList();
        }
        List<AssessmentRecord> filtered = records.stream()
                .filter(r -> planId.equals(r.getPlanId()) && r.getStatus() != null && r.getStatus() == 1)
                .collect(Collectors.toList());
        AssessmentPlan plan = assessmentMapper.getPlanById(planId);
        PreAssessmentTemplate template = plan != null ? preAssessmentTemplateMapper.getTemplateById(plan.getTemplateId()) : null;

        List<AssessmentResultVO> list = new ArrayList<>();
        for (AssessmentRecord r : filtered) {
            AssessmentResultVO vo = new AssessmentResultVO();
            vo.setRecordId(r.getRecordId());
            vo.setPlanId(r.getPlanId());
            if (plan != null) {
                vo.setPlanName(plan.getPlanName());
            }
            if (template != null) {
                vo.setScaleType(template.getScaleType());
                vo.setScaleName(template.getScaleName());
            }
            vo.setTotalScore(r.getTotalScore());
            vo.setScoreInterpretation(r.getScoreInterpretation());
            vo.setRiskLevel(r.getRiskLevel());
            vo.setSubmittedAt(r.getSubmittedAt());
            list.add(vo);
        }
        return list;
    }

    private AssessmentPlanVO toPlanVO(AssessmentPlan plan) {
        if (plan == null) {
            return null;
        }
        AssessmentPlanVO vo = new AssessmentPlanVO();
        vo.setPlanId(plan.getPlanId());
        vo.setPlanName(plan.getPlanName());
        vo.setTemplateId(plan.getTemplateId());
        vo.setTargetRole(plan.getTargetRole());
        vo.setStartTime(plan.getStartTime());
        vo.setEndTime(plan.getEndTime());
        vo.setMaxAttempts(plan.getMaxAttempts());
        vo.setStatus(plan.getStatus());
        return vo;
    }

    private List<AssessmentQuestionVO> toQuestionVOList(List<PreAssessmentQuestion> questions) {
        if (CollectionUtils.isEmpty(questions)) {
            return Collections.emptyList();
        }
        List<AssessmentQuestionVO> list = new ArrayList<>();
        for (PreAssessmentQuestion q : questions) {
            AssessmentQuestionVO vo = new AssessmentQuestionVO();
            vo.setQuestionId(q.getQuestionId());
            vo.setQuestionNo(q.getQuestionNo());
            vo.setQuestionType(q.getQuestionType());
            vo.setQuestionText(q.getQuestionText());
            vo.setRequiredFlag(q.getRequiredFlag());
            if (StringUtils.hasText(q.getOptionsJson())) {
                try {
                    Object opts = objectMapper.readValue(q.getOptionsJson(), Object.class);
                    vo.setOptions(opts);
                } catch (Exception e) {
                    log.warn("解析题目选项失败: {}", e.getMessage());
                }
            }
            list.add(vo);
        }
        return list;
    }

    private int calculateScore(PreAssessmentQuestion q, Object answer) {
        if (answer == null || !StringUtils.hasText(q.getOptionsJson())) {
            return 0;
        }
        try {
            List<Map<String, Object>> options = objectMapper.readValue(q.getOptionsJson(),
                    new TypeReference<List<Map<String, Object>>>() {
                    });
            if (options == null) {
                return 0;
            }
            if (answer instanceof String) {
                String ans = (String) answer;
                for (Map<String, Object> opt : options) {
                    Object value = opt.get("value");
                    Object scoreObj = opt.get("score");
                    if (value != null && ans.equals(String.valueOf(value)) && scoreObj != null) {
                        return Integer.parseInt(String.valueOf(scoreObj));
                    }
                }
            } else if (answer instanceof List) {
                int sum = 0;
                List<?> list = (List<?>) answer;
                for (Object a : list) {
                    String ans = String.valueOf(a);
                    for (Map<String, Object> opt : options) {
                        Object value = opt.get("value");
                        Object scoreObj = opt.get("score");
                        if (value != null && ans.equals(String.valueOf(value)) && scoreObj != null) {
                            sum += Integer.parseInt(String.valueOf(scoreObj));
                        }
                    }
                }
                return sum;
            }
        } catch (Exception e) {
            log.warn("计算题目得分失败: {}", e.getMessage());
        }
        return 0;
    }

    private String simpleInterpretation(int totalScore) {
        if (totalScore <= 0) {
            return "NORMAL";
        }
        if (totalScore <= 5) {
            return "MILD";
        }
        if (totalScore <= 10) {
            return "MODERATE";
        }
        return "SEVERE";
    }

    private String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("JSON序列化失败: " + e.getMessage());
        }
    }
}

