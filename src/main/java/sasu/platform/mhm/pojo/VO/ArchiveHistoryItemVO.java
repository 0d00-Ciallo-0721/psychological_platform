package sasu.platform.mhm.pojo.vo;

import lombok.Data;

/**
 * 归档历史列表项 VO，对应 /api/admin/data/archive/history
 */
@Data
public class ArchiveHistoryItemVO {

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
     * 创建时间 yyyy-MM-dd HH:mm
     */
    private String createdAt;

    /**
     * 操作人
     */
    private String operator;
}


