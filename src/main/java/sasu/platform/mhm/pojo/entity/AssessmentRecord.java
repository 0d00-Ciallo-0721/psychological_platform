package sasu.platform.mhm.pojo.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 平台心理测评记录（一次作答）
 */
@Data
@Builder
public class AssessmentRecord {

    private String recordId;

    private String planId;

    private String templateId;

    private String userId;

    /**
     * 状态：0-进行中 1-已提交 2-作废
     */
    private Integer status;

    private LocalDateTime startedAt;

    private LocalDateTime submittedAt;

    /**
     * 作答明细JSON
     */
    private String answersJson;

    private Integer totalScore;

    /**
     * 维度分JSON，可选
     */
    private String dimensionScoreJson;

    /**
     * 分数解释：NORMAL/MILD/MODERATE/SEVERE 等
     */
    private String scoreInterpretation;

    /**
     * 风险等级：LOW/MID/HIGH 等
     */
    private String riskLevel;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

