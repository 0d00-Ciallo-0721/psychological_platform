package sasu.platform.mhm.pojo.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 心理科普与朋辈互助-内容实体
 */
@Data
public class CmsContent {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 栏目类型：1-活动风采 2-通知公告 3-朋辈互助 4-心理百科 5-心理美文
     */
    private Integer type;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要/简介
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
     * 外链（心理百科学习平台链接等）
     */
    private String linkUrl;

    /**
     * 作者/发布单位
     */
    private String author;

    /**
     * 来源组织（如二级学院）
     */
    private String sourceOrg;

    /**
     * 是否置顶：0-否 1-是（通知公告常用）
     */
    private Boolean isTop;

    /**
     * 状态：0-草稿 1-已发布
     */
    private Integer status;

    /**
     * 排序值，越大越靠前
     */
    private Integer sortOrder;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人ID
     */
    private String creatorId;
}
