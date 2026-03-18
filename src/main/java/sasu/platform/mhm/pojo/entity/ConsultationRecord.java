package sasu.platform.mhm.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 咨询记录实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationRecord {
    /**
     * 记录ID
     */
    private String recordId;

    /**
     * 预约ID
     */
    private String appointmentId;

    /**
     * 咨询师ID
     */
    private String counselorId;

    /**
     * 学生ID
     */
    private String studentId;

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
    private String sessionContent;

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
     * 保密级别: NORMAL-一般, SENSITIVE-敏感, HIGHLY_SENSITIVE-高度敏感
     */
    private String confidentialLevel;

    /**
     * 记录状态: DRAFT-草稿, SUBMITTED-已提交, REVIEWED-已审核
     */
    private String recordStatus;

    /**
     * 审核人
     */
    private String reviewedBy;

    /**
     * 审核时间
     */
    private LocalDateTime reviewedAt;

    /**
     * 审核意见
     */
    private String reviewNotes;

    /**
     * 附件列表(JSON数组)
     */
    private String attachments;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}

