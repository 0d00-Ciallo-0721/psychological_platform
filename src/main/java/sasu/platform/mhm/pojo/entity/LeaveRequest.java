package sasu.platform.mhm.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 学生请假申请实体
 */
@Data
public class LeaveRequest {

    /**
     * 请假ID
     */
    private String leaveId;

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 审批辅导员ID
     */
    private String counselorId;

    /**
     * 请假类型: SICK-病假, PERSONAL-事假 等
     */
    private String leaveType;

    /**
     * 请假原因
     */
    private String reason;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 状态: PENDING-待审批, APPROVED-已批准, REJECTED-已拒绝, CANCELLED-已取消
     */
    private String status;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 审批意见
     */
    private String approveReason;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}


