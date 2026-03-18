package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 更新CMS内容DTO
 */
@Data
public class CmsUpdateDTO {
    /**
     * 内容ID
     */
    private Long id;

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
     * 封面图URL
     */
    private String coverUrl;

    /**
     * 外链
     */
    private String linkUrl;

    /**
     * 作者/发布单位
     */
    private String author;

    /**
     * 来源组织
     */
    private String sourceOrg;

    /**
     * 是否置顶
     */
    private Boolean isTop;

    /**
     * 状态：0-草稿 1-已发布
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

