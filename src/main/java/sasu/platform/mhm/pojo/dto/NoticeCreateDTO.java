package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 创建通知公告DTO
 */
@Data
public class NoticeCreateDTO {
    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 正文内容
     */
    private String content;

    /**
     * 是否置顶
     */
    private Boolean isTop;

    /**
     * 状态: 0-草稿, 1-已发布
     */
    private Integer status;

    /**
     * 排序值
     */
    private Integer sortOrder;

    /**
     * 发布时间
     */
    private String publishTime;
}

