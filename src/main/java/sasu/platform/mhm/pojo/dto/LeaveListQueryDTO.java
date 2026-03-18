package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 请假列表查询 DTO
 */
@Data
public class LeaveListQueryDTO extends PageQueryDTO {

    /**
     * 学生ID（可选，默认当前登录学生）
     */
    private String studentId;

    /**
     * 审批状态过滤
     */
    private String statusFilter;
}


