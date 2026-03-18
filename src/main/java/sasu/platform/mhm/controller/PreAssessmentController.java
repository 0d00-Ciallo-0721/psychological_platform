package sasu.platform.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.PreAssessmentSubmitDTO;
import sasu.platform.mhm.service.PreAssessmentService;

import java.util.Map;

/**
 * 学生提交前测量表接口（写入 pre_assessment 表）
 */
@Slf4j
@RestController
@RequestMapping("/api/pre-assessment")
public class PreAssessmentController {

    @Autowired
    private PreAssessmentService preAssessmentService;

    /**
     * 提交前测量表（电子/手写）
     *
     * 共同必填：appointmentId、scaleType、scaleName、totalScore
     * 电子版必填：assessmentData
     * 手写版必填：handwrittenScaleFile
     */
    @PostMapping("/submit")
    public R submit(@RequestParam String appointmentId,
                    @RequestParam String scaleType,
                    @RequestParam String scaleName,
                    @RequestParam Integer totalScore,
                    @RequestParam(required = false) String scoreInterpretation,
                    @RequestParam(required = false) String assessmentData,
                    @RequestParam(required = false) MultipartFile handwrittenScaleFile) {
        PreAssessmentSubmitDTO dto = new PreAssessmentSubmitDTO();
        dto.setAppointmentId(appointmentId);
        dto.setScaleType(scaleType);
        dto.setScaleName(scaleName);
        dto.setTotalScore(totalScore);
        dto.setScoreInterpretation(scoreInterpretation);
        dto.setAssessmentData(assessmentData);
        dto.setHandwrittenScaleFile(handwrittenScaleFile);

        Map<String, Object> res = preAssessmentService.submit(dto);
        return R.success("提交成功", res);
    }
}


