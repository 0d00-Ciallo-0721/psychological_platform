package sasu.platform.mhm.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sasu.platform.mhm.mapper.CaseMapper;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.CaseCreateDTO;
import sasu.platform.mhm.pojo.dto.CaseListQueryDTO;
import sasu.platform.mhm.pojo.dto.CaseUpdateDTO;
import sasu.platform.mhm.pojo.entity.ConsultationCase;
import sasu.platform.mhm.pojo.entity.UserDetailsImpl;
import sasu.platform.mhm.pojo.vo.CaseVO;
import sasu.platform.mhm.service.CaseService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * 个案服务实现
 */
@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseMapper caseMapper;

    /**
     * 获取当前用户ID
     */
    private String currentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            return userDetails.getUser().getUserId();
        }
        return null;
    }

    @Override
    public PageResult list(CaseListQueryDTO query) {
        if (query == null) {
            query = new CaseListQueryDTO();
        }
        if (query.getPage() == null || query.getPage() <= 0) {
            query.setPage(1);
        }
        if (query.getPageSize() == null || query.getPageSize() <= 0) {
            query.setPageSize(10);
        }

        String userId = currentUserId();
        // 如果没有指定咨询师ID，根据当前用户角色设置
        if (!StringUtils.hasText(query.getCounselorId()) && !StringUtils.hasText(query.getStudentId())) {
            // 可以根据用户角色判断，暂时先不设置
        }

        PageHelper.startPage(query.getPage(), query.getPageSize());
        Page<CaseVO> page = caseMapper.selectCaseList(query);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public CaseVO detail(String caseId) {
        if (!StringUtils.hasText(caseId)) {
            throw new IllegalArgumentException("caseId不能为空");
        }
        CaseVO caseVO = caseMapper.selectCaseById(caseId);
        if (caseVO == null) {
            throw new IllegalArgumentException("个案不存在");
        }
        return caseVO;
    }

    @Override
    public CaseVO create(CaseCreateDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("个案内容不能为空");
        }
        if (!StringUtils.hasText(dto.getStudentId())) {
            throw new IllegalArgumentException("学生ID不能为空");
        }
        if (!StringUtils.hasText(dto.getCounselorId())) {
            throw new IllegalArgumentException("咨询师ID不能为空");
        }

        ConsultationCase consultationCase = new ConsultationCase();
        consultationCase.setCaseId(IdUtil.randomUUID());
        consultationCase.setStudentId(dto.getStudentId());
        consultationCase.setCounselorId(dto.getCounselorId());
        consultationCase.setCaseTitle(dto.getCaseTitle());
        consultationCase.setProblemType(dto.getProblemType());
        consultationCase.setCaseDescription(dto.getCaseDescription());
        consultationCase.setCaseStatus("ONGOING");
        consultationCase.setTotalSessions(0);

        if (StringUtils.hasText(dto.getStartDate())) {
            try {
                consultationCase.setStartDate(LocalDate.parse(dto.getStartDate(), DateTimeFormatter.ISO_DATE));
            } catch (Exception e) {
                consultationCase.setStartDate(LocalDate.now());
            }
        } else {
            consultationCase.setStartDate(LocalDate.now());
        }

        int result = caseMapper.insertCase(consultationCase);
        if (result <= 0) {
            throw new RuntimeException("创建个案失败");
        }

        return caseMapper.selectCaseById(consultationCase.getCaseId());
    }

    @Override
    public CaseVO update(CaseUpdateDTO dto) {
        if (dto == null || !StringUtils.hasText(dto.getCaseId())) {
            throw new IllegalArgumentException("个案ID不能为空");
        }

        ConsultationCase consultationCase = new ConsultationCase();
        consultationCase.setCaseId(dto.getCaseId());
        consultationCase.setCaseTitle(dto.getCaseTitle());
        consultationCase.setCaseStatus(dto.getCaseStatus());
        consultationCase.setProblemType(dto.getProblemType());
        consultationCase.setCaseDescription(dto.getCaseDescription());

        if (StringUtils.hasText(dto.getCloseDate())) {
            try {
                consultationCase.setCloseDate(LocalDate.parse(dto.getCloseDate(), DateTimeFormatter.ISO_DATE));
            } catch (Exception e) {
                // 忽略解析错误
            }
        }

        int result = caseMapper.updateCase(consultationCase);
        if (result <= 0) {
            throw new RuntimeException("更新个案失败");
        }

        return caseMapper.selectCaseById(dto.getCaseId());
    }

    @Override
    public List<Map<String, Object>> stats(String counselorId) {
        return caseMapper.selectCaseStats(counselorId);
    }
}

