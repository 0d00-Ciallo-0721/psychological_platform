package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 转介审批操作 DTO
 */
@Data
public class ReferralApprovalActionDTO {

    /**
     * 转介 ID
     */
    private String id;

    /**
     * 审批意见
     */
    private String comment;

    /**
     * 分配的咨询师（通过时可选）
     */
    private String assignedCounselor;
}


