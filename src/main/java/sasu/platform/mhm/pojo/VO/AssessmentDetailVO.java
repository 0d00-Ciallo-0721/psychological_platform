package sasu.platform.mhm.pojo.VO;

import lombok.Data;

/**
 * 测评详情 VO（说明页）
 */
@Data
public class AssessmentDetailVO {

    private AssessmentPlanVO plan;

    /**
     * 题目数量
     */
    private Integer questionCount;
}

