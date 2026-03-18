package sasu.platform.mhm.pojo.vo;

import lombok.Data;

/**
 * 危机审批列表 VO
 * 对齐前端 LeaderCrisis / CollegeCrisis 使用的字段
 */
@Data
public class CrisisApprovalVO {

    private String id;

    private String level;

    private String levelText;

    private String studentName;

    private String studentId;

    private String collegeName;

    private String className;

    private String reportDate;

    private String description;

    private String status;

    private String statusText;

    private String handler;
}


