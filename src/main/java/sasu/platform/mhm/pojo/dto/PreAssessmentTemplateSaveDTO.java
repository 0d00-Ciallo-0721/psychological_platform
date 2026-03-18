package sasu.platform.mhm.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * 咨询师配置前测量表模板 - 保存DTO（新增/更新）
 */
@Data
public class PreAssessmentTemplateSaveDTO {
    /**
     * 模板ID（更新时传；新增可不传）
     */
    private String templateId;

    /**
     * 量表类型（必填）
     */
    private String scaleType;

    /**
     * 量表名称（必填）
     */
    private String scaleName;

    /**
     * 模板说明（可选）
     */
    private String description;

    /**
     * 状态: 0-停用, 1-启用（可选；默认启用）
     */
    private Integer status;

    /**
     * 题目列表（必填，至少1题）
     */
    private List<QuestionDTO> questions;

    @Data
    public static class QuestionDTO {
        private Integer questionNo;      // 题号（必填）
        private String questionType;     // 题型（必填）
        private String questionText;     // 题干（必填）
        private Object options;          // 选项（可选，序列化为JSON）
        private Boolean required;        // 是否必填（可选，默认true）
        private Object scoreRule;        // 计分规则（可选，序列化为JSON）
    }
}


