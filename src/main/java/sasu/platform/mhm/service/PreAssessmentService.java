package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.dto.PreAssessmentSubmitDTO;

import java.util.Map;

public interface PreAssessmentService {

    /**
     * 学生提交前测量表（电子/手写）
     */
    Map<String, Object> submit(PreAssessmentSubmitDTO dto);
}


