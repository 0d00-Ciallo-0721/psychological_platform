package sasu.platform.mhm.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 创建或更新测评方案 DTO
 */
@Data
public class AssessmentPlanCreateDTO {

    private String planId;

    private String planName;

    private String templateId;

    /**
     * 目标角色编码，如 STUDENT / ALL
     */
    private String targetRole;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer maxAttempts;

    /**
     * 状态：0-停用 1-启用
     */
    private Integer status;
}

