package sasu.platform.mhm.pojo.vo;

import lombok.Data;

/**
 * 管理端-学生列表与详情 VO
 */
@Data
public class AdminStudentVO {

    private String studentId;
    private String userId;
    private String studentNo;
    private String name;
    private String gender;
    private String collegeName;
    private String className;
    private String grade;
    private String phone;
    private String email;
}


