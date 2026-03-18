package sasu.platform.mhm.pojo.VO;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 测评方案列表/详情 VO
 */
@Data
public class AssessmentPlanVO {

    private String planId;

    private String planName;

    private String templateId;

    private String scaleType;

    private String scaleName;

    private String description;

    private String targetRole;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer maxAttempts;

    private Integer status;

    private Integer myAttemptCount;
}

