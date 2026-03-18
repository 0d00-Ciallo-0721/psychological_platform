package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 学生请假申请 DTO
 */
@Data
public class LeaveApplyDTO {

    /**
     * 请假类型
     */
    private String leaveType;

    /**
     * 请假原因
     */
    private String reason;

    /**
     * 开始时间，前端传入 ISO 字符串
     */
    private String startTime;

    /**
     * 结束时间，前端传入 ISO 字符串
     */
    private String endTime;
}


