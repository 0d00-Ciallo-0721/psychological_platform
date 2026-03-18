package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 管理端学生列表查询
 */
@Data
public class AdminStudentQueryDTO extends PageQueryDTO {

    /**
     * 关键字：学号 / 姓名 / 手机
     */
    private String keyword;

    /**
     * 学院ID
     */
    private String collegeId;

    /**
     * 班级ID
     */
    private String classId;

    /**
     * 年级
     */
    private String grade;
}


