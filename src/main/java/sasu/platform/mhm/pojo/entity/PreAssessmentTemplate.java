package sasu.platform.mhm.pojo.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 前测量表模板（咨询师配置）
 */
@Data
@Builder
public class PreAssessmentTemplate {
    private String templateId;
    private String scaleType;
    private String scaleName;
    private String description;
    private String createdBy;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


