package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 请假审批 DTO（同意 / 拒绝）
 */
@Data
public class LeaveApprovalDTO {

    /**
     * 请假ID
     */
    private String leaveId;

    /**
     * 审批意见
     */
    private String reason;
}


