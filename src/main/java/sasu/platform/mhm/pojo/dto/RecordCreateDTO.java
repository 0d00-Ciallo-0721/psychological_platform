package sasu.platform.mhm.pojo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 创建咨询记录DTO
 */
@Data
public class RecordCreateDTO {
    /**
     * 预约ID
     */
    private String appointmentId;

    /**
     * 记录日期
     */
    private String recordDate;

    /**
     * 第几次咨询
     */
    private Integer sessionNumber;

    /**
     * 呈现问题
     */
    private String presentingProblem;

    /**
     * 咨询内容（必填）
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
     * 手写咨询记录图片
     */
    private MultipartFile handwrittenRecordFile;

    /**
     * 保密级别
     */
    private String confidentialLevel;

    /**
     * 记录状态
     */
    private String recordStatus;

    /**
     * 附件图片URL列表
     */
    private List<String> attachments;
}

