package sasu.platform.mhm.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 系统配置实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemConfig {
    /**
     * 配置ID
     */
    private String configId;

    /**
     * 配置键
     */
    private String configKey;

    /**
     * 配置值(JSON格式)
     */
    private String configValue;

    /**
     * 配置类型: VISIT_FORM-来访登记表, PRE_ASSESSMENT-前测量表, CONSENT-知情同意书
     */
    private String configType;

    /**
     * 配置描述
     */
    private String description;

    /**
     * 是否启用: 0-禁用, 1-启用
     */
    private Integer enabled;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 创建人ID
     */
    private String createdBy;
}

