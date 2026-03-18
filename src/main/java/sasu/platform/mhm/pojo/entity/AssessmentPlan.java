package sasu.platform.mhm.pojo.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 平台心理测评方案
 */
@Data
@Builder
public class AssessmentPlan {

    private String planId;

    private String planName;

    /**
     * 关联的量表模板ID（使用已有 pre_assessment_template）
     */
    private String templateId;

    /**
     * 目标人群角色编码，如 STUDENT / ALL
     */
    private String targetRole;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    /**
     * 每个用户最大可作答次数，默认1
     */
    private Integer maxAttempts;

    /**
     * 状态：0-停用 1-启用
     */
    private Integer status;

    private String createdBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

