package sasu.platform.mhm.pojo.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 长程咨询申请表实体，对应 long_term_consultation_request
 */
@Data
public class LongTermConsultationRequest {

    private String requestId;

    private String appointmentId;

    private String counselorId;

    private String studentId;

    private String requestReason;

    private Integer proposedSessions;

    private String treatmentGoals;

    private String expectedOutcomes;

    private String handwrittenRequestUrl;

    private String requestStatus;

    private String approverId;

    private String approvalNotes;

    private Integer approvedSessions;

    private String approvedFrequency;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}


