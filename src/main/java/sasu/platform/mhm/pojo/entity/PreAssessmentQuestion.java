package sasu.platform.mhm.pojo.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 前测量表题目（咨询师配置）
 */
@Data
@Builder
public class PreAssessmentQuestion {
    private String questionId;
    private String templateId;
    private Integer questionNo;
    private String questionType;
    private String questionText;
    private String optionsJson;
    private Integer requiredFlag;
    private String scoreRuleJson;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


