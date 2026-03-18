package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.VO.AssessmentDetailVO;
import sasu.platform.mhm.pojo.VO.AssessmentResultVO;
import sasu.platform.mhm.pojo.VO.AssessmentStartVO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.AssessmentListQueryDTO;
import sasu.platform.mhm.pojo.dto.AssessmentPlanCreateDTO;
import sasu.platform.mhm.pojo.dto.AssessmentStartDTO;
import sasu.platform.mhm.pojo.dto.AssessmentSubmitDTO;

import java.util.List;
import java.util.Map;

public interface AssessmentService {

    /**
     * 测评列表（当前用户可参与的方案）
     */
    PageResult listAssessments(AssessmentListQueryDTO query);

    /**
     * 测评详情（说明页）
     */
    AssessmentDetailVO detail(String planId);

    /**
     * 开始测评
     */
    AssessmentStartVO start(AssessmentStartDTO dto);

    /**
     * 提交测评
     */
    Map<String, Object> submit(AssessmentSubmitDTO dto);

    /**
     * 测评结果
     */
    AssessmentResultVO result(String recordId);

    /**
     * 我的测评
     */
    PageResult my(Integer page, Integer pageSize);

    /**
     * 创建/更新测评方案（管理端）
     */
    Map<String, Object> savePlan(AssessmentPlanCreateDTO dto);

    /**
     * 测评导出（此处只返回元数据，实际导出由 Controller 处理）
     */
    List<AssessmentResultVO> exportByPlan(String planId);
}

