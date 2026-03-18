package sasu.platform.mhm.pojo.vo;

import lombok.Data;
import sasu.platform.mhm.pojo.entity.ConsultationSchedule;

import java.util.Date;
import java.util.List;

@Data
public class CounselorDetailVO {
    private UserVO userVO;                   // 用户信息
    private String counselorId;          // 咨询师ID
    private String employeeNumber;       // 工号
    private String title;                // 职称
    private String qualificationLevel;   // 资质等级
    private String certificateNumber;    // 证书编号
    private String certificateImage;     // 证书图片
    private String educationBackground;  // 教育背景
    private String graduationSchool;     // 毕业院校
    private String majorField;           // 专业领域
    private String counselingApproach;   // 咨询流派
    private String expertiseAreas;       // 擅长领域
    private String introduction;         // 个人简介
    private Integer workExperienceYears; // 工作年限
    private Boolean isFullTime;          // 是否专职: 0-兼职, 1-专职
    private Integer counselorStatus;     // 咨询师状态: 0-停用, 1-正常, 2-休假
    private Integer maxCasesPerWeek;     // 每周最大个案数
    private Date createdAt;              // 创建时间
    private Date updatedAt;              // 更新时间
    private ConsultationSchedule consultationSchedule;//咨询时间表
}
