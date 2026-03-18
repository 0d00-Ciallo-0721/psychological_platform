package sasu.platform.mhm.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 请假记录 VO
 */
@Data
public class LeaveVO {

    private String leaveId;

    private String studentId;

    private String counselorId;

    private String leaveType;

    private String reason;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String status;

    private String cancelReason;

    private String approveReason;

    private LocalDateTime createdAt;
}


