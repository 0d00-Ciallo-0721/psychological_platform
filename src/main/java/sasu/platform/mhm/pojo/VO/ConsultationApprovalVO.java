package sasu.platform.mhm.pojo.vo;

import lombok.Data;

/**
 * 咨询审批（长程申请）列表 VO
 * 对齐前端审批列表展示字段
 */
@Data
public class ConsultationApprovalVO {

    private String id;

    private String studentName;

    private String studentId;

    private String collegeName;

    private String counselorName;

    private String requestReason;

    private Integer proposedSessions;

    private String requestStatus;

    private String statusText;

    private Integer approvedSessions;

    private String approvedFrequency;

    private String startDate;

    private String endDate;

    private String createdAt;
}


