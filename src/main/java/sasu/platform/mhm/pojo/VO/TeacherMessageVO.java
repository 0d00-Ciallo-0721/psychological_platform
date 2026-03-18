package sasu.platform.mhm.pojo.vo;

import lombok.Data;

/**
 * 教师/辅导员收件箱消息 VO
 */
@Data
public class TeacherMessageVO {

    /**
     * 留言 ID
     */
    private String id;

    /**
     * 学生 ID，可选
     */
    private String studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 发送方角色: parent / student / leader
     */
    private String fromRole;

    /**
     * 发送方角色文案，如“家长”
     */
    private String fromRoleText;

    /**
     * 留言内容
     */
    private String content;

    /**
     * 时间（前端展示用）
     */
    private String time;

    /**
     * 是否已读
     */
    private Boolean read;

    /**
     * 是否已回复
     */
    private Boolean replied;

    /**
     * 回复内容
     */
    private String reply;
}


