package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 预约状态更新DTO
 */
@Data
public class AppointmentStatusUpdateDTO {
    /**
     * 预约ID
     */
    private String id;

    /**
     * 目标状态: CONFIRMED-已确认, CANCELLED-已取消, REJECTED-已拒绝, NO_SHOW-爽约, COMPLETED-已完成
     */
    private String status;

    /**
     * 取消原因（取消时必填）
     */
    private String reason;

    /**
     * 其他字段（用于更新）
     */
    private String notes;
}

