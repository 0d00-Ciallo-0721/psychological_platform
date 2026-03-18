package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 绑定子女DTO
 */
@Data
public class ParentBindChildDTO {
    /**
     * 学生ID或学号
     */
    private String studentId;

    /**
     * 学生姓名（用于验证）
     */
    private String studentName;

    /**
     * 关系类型
     */
    private String relationType;

    /**
     * 验证信息（如身份证号后4位等）
     */
    private String verificationInfo;
}

