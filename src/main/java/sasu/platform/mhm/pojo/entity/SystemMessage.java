package sasu.platform.mhm.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 系统消息实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemMessage {
    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 消息类型: APPOINTMENT-预约, REMINDER-提醒, NOTIFICATION-通知, ALERT-警报, CRISIS-危机, ASSESSMENT-测评, ACTIVITY-活动, SYSTEM-系统
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
     * 关联ID(如预约ID、测评ID等)
     */
    private String relatedId;

    /**
     * 是否已读: 0-未读, 1-已读
     */
    private Integer isRead;

    /**
     * 阅读时间
     */
    private LocalDateTime readAt;

    /**
     * 计划发送时间
     */
    private LocalDateTime scheduledSendTime;

    /**
     * 实际发送时间
     */
    private LocalDateTime sentAt;

    /**
     * 过期时间
     */
    private LocalDateTime expiresAt;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 优先级: 0-普通, 1-重要
     */
    private Integer priority;

    /**
     * 链接地址（前端跳转用）
     */
    private String link;
}

