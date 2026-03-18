package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 咨询审批（长程申请）操作 DTO
 * 对齐 /api/center/approval/* approve/reject body
 */
@Data
public class ConsultationApprovalActionDTO {

    /**
     * 申请 ID
     */
    private String id;

    /**
     * 审批意见
     */
    private String comment;

    /**
     * 批准的次数（通过时）
     */
    private Integer approvedSessions;

    /**
     * 批准的频率（如 weekly, biweekly）
     */
    private String approvedFrequency;

    /**
     * 开始日期（通过时）
     */
    private String startDate;

    /**
     * 结束日期（通过时）
     */
    private String endDate;
}


