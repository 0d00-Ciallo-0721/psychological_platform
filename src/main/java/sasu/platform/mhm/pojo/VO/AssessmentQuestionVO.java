package sasu.platform.mhm.pojo.VO;

import lombok.Data;

/**
 * 测评题目 VO（从 PreAssessmentQuestion 映射）
 */
@Data
public class AssessmentQuestionVO {

    private String questionId;

    private Integer questionNo;

    private String questionType;

    private String questionText;

    private Object options;

    private Integer requiredFlag;
}

