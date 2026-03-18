package sasu.platform.mhm.pojo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 学生提交前测量表DTO
 *
 * - 电子版：不传handwrittenScaleFile，必须传assessmentData
 * - 手写版：必须传handwrittenScaleFile，assessmentData可不传
 *
 * 共同必填：appointmentId、scaleType、scaleName、totalScore
 */
@Data
public class PreAssessmentSubmitDTO {
    private String appointmentId;          // 必填
    private String scaleType;              // 必填
    private String scaleName;              // 必填
    private Integer totalScore;            // 必填

    private String scoreInterpretation;    // 可选
    private String assessmentData;         // 电子版必填（JSON字符串）

    private MultipartFile handwrittenScaleFile; // 手写版必填
}


