package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 危机审批列表查询 DTO
 * 对齐前端 LeaderCrisis / CollegeCrisis 使用的查询参数
 */
@Data
public class CrisisApprovalQueryDTO extends PageQueryDTO {

    /**
     * 关键字（按学生姓名/学号等模糊匹配）
     */
    private String keyword;

    /**
     * 学院 ID
     */
    private String college;

    /**
     * 危机等级（red/orange/yellow/blue 等）
     */
    private String level;

    /**
     * 审批状态：pending / processing / closed / all
     * 避免与 PageQueryDTO.status(Integer) 冲突
     */
    private String approvalStatus;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;
}


