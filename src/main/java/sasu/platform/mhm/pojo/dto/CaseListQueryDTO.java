package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 个案列表查询DTO
 */
@Data
public class CaseListQueryDTO extends PageQueryDTO {
    /**
     * 咨询师ID
     */
    private String counselorId;

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 个案状态（避免与 PageQueryDTO.status 冲突，使用 caseStatus）
     */
    private String caseStatus;

    /**
     * 问题类型
     */
    private String problemType;
}

