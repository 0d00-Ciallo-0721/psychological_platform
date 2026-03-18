package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 数据归档配置请求体
 * 对应 POST /api/admin/data/archive/config
 */
@Data
public class ArchiveConfigDTO {

    /**
     * 是否开启自动归档
     */
    private Boolean enabled;

    /**
     * 归档周期：monthly / quarterly / yearly
     */
    private String cycle;

    /**
     * 在线保留年限
     */
    private Integer keepYears;

    /**
     * 归档文件存储路径（或存储桶标识）
     */
    private String archivePath;
}


