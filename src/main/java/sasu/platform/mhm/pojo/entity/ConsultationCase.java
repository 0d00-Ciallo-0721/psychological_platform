package sasu.platform.mhm.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 咨询个案实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationCase {
    /**
     * 个案ID
     */
    private String caseId;

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
     * 个案状态: ONGOING-进行中, CLOSED-已结案, SUSPENDED-暂停
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
     * 开始日期
     */
    private LocalDate startDate;

    /**
     * 结案日期
     */
    private LocalDate closeDate;

    /**
     * 总咨询次数
     */
    private Integer totalSessions;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}

