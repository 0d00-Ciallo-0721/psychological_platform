package sasu.platform.mhm.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 数据归档历史记录实体，对应 archive_history 表
 */
@Data
public class ArchiveHistory {

    /**
     * 归档任务ID
     */
    private String id;

    /**
     * 归档周期描述（如 2024-Q1、2024）
     */
    private String period;

    /**
     * 状态：success / failed / running
     */
    private String status;

    /**
     * 归档文件数量
     */
    private Integer fileCount;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 操作人
     */
    private String operator;
}


