package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 消息列表查询DTO
 */
@Data
public class MessageListQueryDTO extends PageQueryDTO {

    /**
     * 是否已读: true-已读, false-未读, null-全部
     */
    private Boolean read;

    /**
     * 用户ID（从token中获取，这里可选）
     */
    private String userId;

    /**
     * 是否只返回数量（用于未读数量查询）
     */
    private Boolean countOnly;
}

