package sasu.platform.mhm.pojo.VO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 测评结果 VO
 */
@Data
public class AssessmentResultVO {

    private String recordId;

    private String planId;

    private String planName;

    private String scaleType;

    private String scaleName;

    private Integer totalScore;

    private String scoreInterpretation;

    private String riskLevel;

    private Map<String, Object> dimensionScores;

    private LocalDateTime submittedAt;
}

