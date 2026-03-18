package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 更新个案DTO
 */
@Data
public class CaseUpdateDTO {
    /**
     * 个案ID
     */
    private String caseId;

    /**
     * 个案标题
     */
    private String caseTitle;

    /**
     * 个案状态
     */
    private String caseStatus;

    /**
     * 问题类型
     */
    private String problemType;

    /**
     * 个案描述
     */
    private String caseDescription;

    /**
     * 结案日期
     */
    private String closeDate;
}

