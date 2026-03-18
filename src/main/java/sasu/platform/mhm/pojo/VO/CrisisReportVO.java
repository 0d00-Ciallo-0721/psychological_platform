package sasu.platform.mhm.pojo.VO;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 危机上报列表返回VO
 */
@Data
public class CrisisReportVO {
    /** 上报ID */
    private String reportId;
    /** 学生ID */
    private String studentId;
    /** 学生姓名 */
    private String studentName;
    /** 学院 */
    private String college;
    /** 危机等级（red/orange/yellow/blue/green） */
    private String crisisLevel;
    /** 危机等级描述 */
    private String reportType;
    /** 处理状态（processing/closed） */
    private String reportStatus;
    /** 处理状态描述 */
    private String incidentDescription;
    /** 上报人 */
    private String reporter;
    /** 上报时间 */
    private LocalDateTime createdAt;
    /** 最后处理时间 */
    private LocalDateTime updatedAt;
}