package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 教师/辅导员回复留言 DTO
 */
@Data
public class TeacherReplyDTO {

    /**
     * 被回复的消息ID
     */
    private String messageId;

    /**
     * 回复内容
     */
    private String content;
}


