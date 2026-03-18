package sasu.platform.mhm.pojo.vo;

import lombok.Data;

/**
 * 子女信息VO
 */
@Data
public class ChildVO {
    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学号
     */
    private String studentNumber;

    /**
     * 学院
     */
    private String college;

    /**
     * 专业
     */
    private String major;

    /**
     * 年级
     */
    private String grade;

    /**
     * 班级
     */
    private String className;

    /**
     * 头像
     */
    private String avatar;
}

