package sasu.platform.mhm.pojo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 更新咨询记录DTO
 */
@Data
public class RecordUpdateDTO {
    /**
     * 记录ID
     */
    private String recordId;

    /**
     * 记录状态: DRAFT-草稿, SUBMITTED-已提交
     */
    private String recordStatus;

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
     * 呈现问题
     */
    private String presentingProblem;

    /**
     * 保密级别
     */
    private String confidentialLevel;

    /**
     * 附件图片URL列表
     */
    private List<String> attachments;
}

