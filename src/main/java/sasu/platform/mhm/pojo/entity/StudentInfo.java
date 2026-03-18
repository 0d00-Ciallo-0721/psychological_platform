package sasu.platform.mhm.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 学生基础信息，对应 student_info 表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfo {

    private String studentId;
    private String userId;
    private String studentNo;
    private String name;
    private String gender;
    private LocalDate birthday;
    private String collegeId;
    private String classId;
    private String grade;
    private String phone;
    private String email;
}


