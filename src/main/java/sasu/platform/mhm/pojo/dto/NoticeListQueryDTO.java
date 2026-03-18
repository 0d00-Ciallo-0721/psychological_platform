package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 通知公告列表查询DTO
 */
@Data
public class NoticeListQueryDTO extends PageQueryDTO {
    /**
     * 关键词搜索（标题、摘要）
     */
    private String keyword;

    /**
     * 是否置顶: true-置顶, false-非置顶, null-全部
     */
    private Boolean isTop;

    /**
     * 状态: 1-已发布, 0-草稿, null-全部
     */
    private Integer status;
}

