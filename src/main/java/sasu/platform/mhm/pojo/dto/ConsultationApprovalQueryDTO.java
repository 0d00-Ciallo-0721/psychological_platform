package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 咨询审批（长程申请）列表查询 DTO
 * 对齐 /api/center/approval/* 中 long-term 列表查询参数
 */
@Data
public class ConsultationApprovalQueryDTO extends PageQueryDTO {

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


