package sasu.platform.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sasu.platform.mhm.pojo.VO.AssessmentDetailVO;
import sasu.platform.mhm.pojo.VO.AssessmentResultVO;
import sasu.platform.mhm.pojo.VO.AssessmentStartVO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.AssessmentListQueryDTO;
import sasu.platform.mhm.pojo.dto.AssessmentPlanCreateDTO;
import sasu.platform.mhm.pojo.dto.AssessmentStartDTO;
import sasu.platform.mhm.pojo.dto.AssessmentSubmitDTO;
import sasu.platform.mhm.service.AssessmentService;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/assessment")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    /**
     * 测评列表
     */
    @GetMapping("/list")
    public R<PageResult> list(AssessmentListQueryDTO query) {
        log.info("测评列表查询: {}", query);
        PageResult result = assessmentService.listAssessments(query);
        return R.success(result);
    }

    /**
     * 测评详情（说明页）
     */
    @GetMapping("/detail")
    public R<AssessmentDetailVO> detail(@RequestParam(value = "planId", required = false) String planId,
                                        @RequestParam(value = "id", required = false) String id) {
        String finalId = planId != null ? planId : id;
        log.info("测评详情: {}", finalId);
        AssessmentDetailVO detail = assessmentService.detail(finalId);
        return R.success(detail);
    }

    /**
     * 开始测评
     */
    @PostMapping("/start")
    public R<AssessmentStartVO> start(@RequestBody AssessmentStartDTO dto) {
        log.info("开始测评: {}", dto);
        AssessmentStartVO vo = assessmentService.start(dto);
        return R.success(vo);
    }

    /**
     * 提交测评
     */
    @PostMapping("/submit")
    public R<Map<String, Object>> submit(@RequestBody AssessmentSubmitDTO dto) {
        log.info("提交测评: {}", dto);
        Map<String, Object> res = assessmentService.submit(dto);
        return R.success(res);
    }

    /**
     * 测评结果
     */
    @GetMapping("/result")
    public R<AssessmentResultVO> result(@RequestParam(value = "recordId", required = false) String recordId,
                                        @RequestParam(value = "id", required = false) String id) {
        String finalId = recordId != null ? recordId : id;
        log.info("测评结果: {}", finalId);
        AssessmentResultVO vo = assessmentService.result(finalId);
        return R.success(vo);
    }

    /**
     * 我的测评
     */
    @GetMapping("/my")
    public R<PageResult> my(@RequestParam Integer page,
                            @RequestParam Integer pageSize) {
        log.info("我的测评: page={}, pageSize={}", page, pageSize);
        PageResult result = assessmentService.my(page, pageSize);
        return R.success(result);
    }

    /**
     * 我的测评（前端别名：/api/assessment/my-list）
     */
    @GetMapping("/my-list")
    public R<PageResult> myList(@RequestParam Integer page,
                                @RequestParam Integer pageSize) {
        log.info("我的测评(my-list): page={}, pageSize={}", page, pageSize);
        PageResult result = assessmentService.my(page, pageSize);
        return R.success(result);
    }

    /**
     * 测评方案创建/更新（管理端）
     */
    @PostMapping("/plan/create")
    public R<Map<String, Object>> savePlan(@RequestBody AssessmentPlanCreateDTO dto) {
        log.info("保存测评方案: {}", dto);
        Map<String, Object> res = assessmentService.savePlan(dto);
        return R.success("保存成功", res);
    }

    /**
     * 测评导出（返回结果列表，前端自行导出或后续扩展为文件流）
     */
    @GetMapping("/export")
    public R<List<AssessmentResultVO>> export(@RequestParam String planId) {
        log.info("测评导出, planId={}", planId);
        List<AssessmentResultVO> list = assessmentService.exportByPlan(planId);
        return R.success(list);
    }

    /**
     * 测评导出（POST 方式，兼容部分前端实现）
     */
    @PostMapping("/export")
    public R<List<AssessmentResultVO>> exportPost(@RequestBody Map<String, String> body) {
        String planId = body.get("planId");
        log.info("测评导出(POST), planId={}", planId);
        List<AssessmentResultVO> list = assessmentService.exportByPlan(planId);
        return R.success(list);
    }
}

