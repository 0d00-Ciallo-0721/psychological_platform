package sasu.platform.mhm.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 数据归档配置实体，对应 data_archive_config 表
 */
@Data
public class ArchiveConfig {

    /**
     * 主键ID（一般固定为 1）
     */
    private Integer id;

    /**
     * 是否启用：1 启用，0 禁用
     */
    private Integer enabled;

    /**
     * 归档周期：monthly / quarterly / yearly
     */
    private String cycle;

    /**
     * 在线保留年限
     */
    private Integer keepYears;

    /**
     * 归档文件存储路径
     */
    private String archivePath;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}


