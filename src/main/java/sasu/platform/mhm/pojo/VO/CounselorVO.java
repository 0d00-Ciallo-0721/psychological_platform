package sasu.platform.mhm.pojo.vo;

import lombok.Data;

/**
 * 辅导员列表 VO
 */
@Data
public class CounselorVO {

    /**
     * 辅导员 ID
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 职务/职称
     */
    private String title;

    /**
     * 学院名称
     */
    private String collegeName;

    /**
     * 头像 URL
     */
    private String avatar;

    /**
     * 办公室
     */
    private String office;

    /**
     * 值班时间
     */
    private String officeHours;

    /**
     * 简介
     */
    private String intro;
}

