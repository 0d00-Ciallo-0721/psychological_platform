package sasu.platform.mhm.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 转介记录实体，对应表 referral_record
 */
@Data
public class ReferralRecord {

    private String referralId;

    private String appointmentId;

    private String sourceCounselorId;

    private String targetCounselorId;

    private String targetDepartment;

    private String studentId;

    private String referralReason;

    private String studentCondition;

    private String handwrittenReferralUrl;

    private String referralUrgency;

    private String referralStatus;

    private String targetResponse;

    private LocalDateTime responseDate;

    private String followUpNotes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}


