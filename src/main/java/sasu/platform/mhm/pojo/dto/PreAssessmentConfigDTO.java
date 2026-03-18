package sasu.platform.mhm.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * 前测量表配置DTO
 */
@Data
public class PreAssessmentConfigDTO {
    /**
     * 量表列表
     */
    private List<ScaleConfig> scales;

    @Data
    public static class ScaleConfig {
        /**
         * 量表ID
         */
        private String id;

        /**
         * 量表标题
         */
        private String title;

        /**
         * 是否启用
         */
        private Boolean enabled;
    }
}

