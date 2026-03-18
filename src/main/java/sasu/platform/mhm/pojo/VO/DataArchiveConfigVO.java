package sasu.platform.mhm.pojo.vo;

import lombok.Data;

/**
 * 数据归档配置 VO，对应 /api/admin/data/archive/config
 */
@Data
public class DataArchiveConfigVO {

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


