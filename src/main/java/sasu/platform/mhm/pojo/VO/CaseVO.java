package sasu.platform.mhm.pojo.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 个案VO
 */
@Data
public class CaseVO {
    /**
     * 个案ID
     */
    private String id;

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 咨询师ID
     */
    private String counselorId;

    /**
     * 咨询师姓名
     */
    private String counselorName;

    /**
     * 个案标题
     */
    private String caseTitle;

    /**
     * 个案状态
     */
    private String status;

    /**
     * 问题类型
     */
    private String problemType;

    /**
     * 个案描述
     */
    private String description;

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
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 进度描述
     */
    private String progress;
}

