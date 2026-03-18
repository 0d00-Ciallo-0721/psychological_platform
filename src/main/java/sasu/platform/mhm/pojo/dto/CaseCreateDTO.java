package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 创建个案DTO
 */
@Data
public class CaseCreateDTO {
    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 咨询师ID
     */
    private String counselorId;

    /**
     * 个案标题
     */
    private String caseTitle;

    /**
     * 问题类型
     */
    private String problemType;

    /**
     * 个案描述
     */
    private String caseDescription;

    /**
     * 开始日期
     */
    private String startDate;
}

