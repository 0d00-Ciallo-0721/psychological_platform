package sasu.platform.mhm.pojo.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 前测量表（学生填写结果存储）
 */
@Data
@Builder
public class PreAssessment {
    private String assessmentId;
    private String appointmentId;
    private String scaleType;
    private String scaleName;
    private Integer totalScore;
    private String scoreInterpretation;
    private String assessmentData;
    private String handwrittenScaleUrl;
    private LocalDateTime completedAt;
    private LocalDateTime createdAt;
}


