package sasu.platform.mhm.pojo.dto;

import lombok.Data;

import java.sql.Time;

/**
 * 创建排班DTO
 */
@Data
public class ScheduleCreateDTO {
    /**
     * 咨询师ID
     */
    private String counselorId;

    /**
     * 排班日期(星期): 1-周一, 2-周二, ..., 7-周日
     */
    private Integer scheduleDate;

    /**
     * 开始时间
     */
    private Time startTime;

    /**
     * 结束时间
     */
    private Time endTime;

    /**
     * 单次咨询时长(分钟)
     */
    private String slotDuration;

    /**
     * 剩余可预约数
     */
    private Integer availableSlots;

    /**
     * 排班类型: REGULAR-常规, SPECIAL-特殊, HOLIDAY-节假日
     */
    private String scheduleType;

    /**
     * 状态: 0-停用, 1-启用, 2-已满
     */
    private Integer status;

    /**
     * 预约地点
     */
    private String reservationLocation;
}

