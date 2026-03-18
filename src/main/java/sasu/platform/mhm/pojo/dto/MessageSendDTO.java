package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 发送消息DTO
 */
@Data
public class MessageSendDTO {
    /**
     * 接收用户ID
     */
    private String userId;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 关联ID
     */
    private String relatedId;

    /**
     * 链接地址
     */
    private String link;

    /**
     * 优先级: 0-普通, 1-重要
     */
    private Integer priority;
}

