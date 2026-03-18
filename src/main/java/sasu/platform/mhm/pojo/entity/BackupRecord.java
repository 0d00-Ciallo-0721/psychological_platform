package sasu.platform.mhm.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 数据备份记录实体，对应 backup_record 表
 */
@Data
public class BackupRecord {

    /**
     * 备份ID
     */
    private String id;

    /**
     * 备份文件名
     */
    private String fileName;

    /**
     * 文件大小（字节）
     */
    private Long size;

    /**
     * 状态：success / failed / running
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 操作人
     */
    private String operator;
}


