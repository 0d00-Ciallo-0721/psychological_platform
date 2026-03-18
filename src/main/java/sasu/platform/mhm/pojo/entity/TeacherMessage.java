package sasu.platform.mhm.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 辅导员/教师留言消息实体
 */
@Data
public class TeacherMessage {

    private String messageId;

    private String senderId;

    private String receiverId;

    private String studentId;

    private String parentId;

    private String subject;

    private String content;

    /**
     * 发送方角色: parent / student / leader
     */
    private String fromRole;

    private String replyToId;

    private Boolean isRead;

    /**
     * 是否已回复
     */
    private Boolean replied;

    /**
     * 最近一条回复内容
     */
    private String replyContent;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}


