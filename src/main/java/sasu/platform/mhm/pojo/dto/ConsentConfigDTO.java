package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 知情同意书配置DTO
 */
@Data
public class ConsentConfigDTO {
    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 是否强制
     */
    private Boolean force;

    /**
     * 文件URL
     */
    private String fileUrl;
}

