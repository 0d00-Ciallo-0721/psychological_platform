package sasu.platform.mhm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sasu.platform.mhm.mapper.PreAssessmentMapper;
import sasu.platform.mhm.pojo.dto.PreAssessmentSubmitDTO;
import sasu.platform.mhm.pojo.entity.PreAssessment;
import sasu.platform.mhm.service.PreAssessmentService;
import sasu.platform.mhm.util.OssUtil;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
public class PreAssessmentServiceImpl implements PreAssessmentService {

    @Autowired
    private PreAssessmentMapper preAssessmentMapper;

    @Autowired
    private OssUtil ossUtil;

    @Override
    public Map<String, Object> submit(PreAssessmentSubmitDTO dto) {
        if (dto == null) {
            throw new RuntimeException("参数不能为空");
        }

        // 共同必填（手写/电子都必须提供）
        if (!StringUtils.hasText(dto.getAppointmentId())) {
            throw new RuntimeException("appointmentId不能为空");
        }
        if (!StringUtils.hasText(dto.getScaleType())) {
            throw new RuntimeException("scaleType不能为空");
        }
        if (!StringUtils.hasText(dto.getScaleName())) {
            throw new RuntimeException("scaleName不能为空");
        }
        if (dto.getTotalScore() == null) {
            throw new RuntimeException("totalScore不能为空");
        }

        boolean isHandwritten = dto.getHandwrittenScaleFile() != null && !dto.getHandwrittenScaleFile().isEmpty();

        String handwrittenUrl = null;
        if (isHandwritten) {
            MultipartFile file = dto.getHandwrittenScaleFile();
            handwrittenUrl = ossUtil.uploadFile(file, "pre-assessment/");
        } else {
            // 电子版必须提供assessmentData（JSON字符串）
            if (!StringUtils.hasText(dto.getAssessmentData())) {
                throw new RuntimeException("assessmentData不能为空(电子版必填)");
            }
        }

        PreAssessment preAssessment = PreAssessment.builder()
                .appointmentId(dto.getAppointmentId())
                .scaleType(dto.getScaleType())
                .scaleName(dto.getScaleName())
                .totalScore(dto.getTotalScore())
                .scoreInterpretation(dto.getScoreInterpretation())
                .assessmentData(dto.getAssessmentData())
                .handwrittenScaleUrl(handwrittenUrl)
                .completedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        boolean ok = preAssessmentMapper.insert(preAssessment);
        if (!ok) {
            throw new RuntimeException("提交失败");
        }
        return Map.of("submitted", true);
    }
}


