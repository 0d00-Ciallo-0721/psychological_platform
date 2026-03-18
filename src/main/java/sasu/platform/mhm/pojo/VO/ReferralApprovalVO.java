package sasu.platform.mhm.pojo.vo;

import lombok.Data;

/**
 * 转介审批列表 VO
 */
@Data
public class ReferralApprovalVO {

    private String id;

    private String studentName;

    private String studentId;

    private String collegeName;

    private String sourceCounselorName;

    private String targetCounselorName;

    private String targetDepartment;

    private String urgency;

    private String status;

    private String statusText;

    private String createdAt;
}


