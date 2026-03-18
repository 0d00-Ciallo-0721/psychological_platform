package sasu.platform.mhm.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 家长-子女关联实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentStudentRelation {
    /**
     * 关联ID
     */
    private String relationId;

    /**
     * 家长ID
     */
    private String parentId;

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 关系类型: PARENT-父母, GUARDIAN-监护人
     */
    private String relationType;

    /**
     * 验证状态: 0-待验证, 1-已验证
     */
    private Integer verificationStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}

