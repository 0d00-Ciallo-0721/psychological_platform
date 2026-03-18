package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 转介审批列表查询 DTO
 * 对齐 openapi: type, status, page, pageSize + 常用筛选
 */
@Data
public class ReferralApprovalQueryDTO extends PageQueryDTO {

    /**
     * 转介类型（预留），避免与 PageQueryDTO.type(int) 冲突
     */
    private String referralType;

    /**
     * 审批状态：pending / approved / rejected / all
     * 避免与 PageQueryDTO.status(Integer) 冲突
     */
    private String approvalStatus;

    /**
     * 关键字（学生姓名/学号）
     */
    private String keyword;
}


