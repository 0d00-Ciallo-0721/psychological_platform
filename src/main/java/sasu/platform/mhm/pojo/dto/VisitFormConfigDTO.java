package sasu.platform.mhm.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * 来访登记表配置DTO
 */
@Data
public class VisitFormConfigDTO {
    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 字段配置列表
     */
    private List<FieldConfig> fields;

    @Data
    public static class FieldConfig {
        /**
         * 字段键名
         */
        private String key;

        /**
         * 字段标签
         */
        private String label;

        /**
         * 是否必填
         */
        private Boolean required;
    }
}

