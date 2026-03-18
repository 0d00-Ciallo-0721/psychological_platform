package sasu.platform.mhm.pojo.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 咨询记录VO
 */
@Data
public class RecordVO {
    /**
     * 记录ID
     */
    private String id;

    /**
     * 预约ID
     */
    private String appointmentId;

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 咨询师ID
     */
    private String counselorId;

    /**
     * 咨询师姓名
     */
    private String counselorName;

    /**
     * 记录日期
     */
    private LocalDate recordDate;

    /**
     * 第几次咨询
     */
    private Integer sessionNumber;

    /**
     * 呈现问题
     */
    private String presentingProblem;

    /**
     * 咨询内容
     */
    private String content;

    /**
     * 摘要（从咨询内容中提取或单独字段）
     */
    private String summary;

    /**
     * 问题类型
     */
    private String problemType;

    /**
     * 咨询师观察
     */
    private String counselorObservations;

    /**
     * 干预方法
     */
    private String interventionMethods;

    /**
     * 家庭作业
     */
    private String homeworkAssignment;

    /**
     * 下次咨询计划
     */
    private String nextSessionPlan;

    /**
     * 咨询师备注
     */
    private String counselorNotes;

    /**
     * 手写咨询记录图片URL
     */
    private String handwrittenRecordUrl;

    /**
     * 保密级别
     */
    private String confidentialLevel;

    /**
     * 记录状态
     */
    private String status;

    /**
     * 附件图片URL列表
     */
    private List<String> images;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}

