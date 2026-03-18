package sasu.platform.mhm.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 预约详情VO
 */
@Data
public class AppointmentDetailVO {
    /**
     * 预约ID
     */
    private String id;

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 咨询师ID
     */
    private String counselorId;

    /**
     * 咨询师姓名
     */
    private String counselorName;

    /**
     * 排班ID
     */
    private String scheduleId;

    /**
     * 预约日期
     */
    private String appointmentDate;

    /**
     * 预约时间
     */
    private String appointmentTime;

    /**
     * 咨询类型
     */
    private String appointmentType;

    /**
     * 咨询方式
     */
    private String consultationMode;

    /**
     * 地点
     */
    private String location;

    /**
     * 预约状态
     */
    private String status;

    /**
     * 取消原因
     */
    private String cancellationReason;

    /**
     * 取消人
     */
    private String cancelledBy;

    /**
     * 是否紧急
     */
    private Boolean isUrgent;

    /**
     * 备注
     */
    private String notes;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}

