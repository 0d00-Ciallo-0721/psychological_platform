package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 咨询记录列表查询DTO
 */
@Data
public class RecordListQueryDTO extends PageQueryDTO {
    /**
     * 咨询师ID
     */
    private String counselorId;

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 预约ID
     */
    private String appointmentId;

    /**
     * 记录状态
     */
    private String recordStatus;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;
}

