package sasu.platform.mhm.pojo.dto;

//import com.baomidou.mybatisplus.annotation.TableId;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class CrisisReportCreateDTO {
    /**
     * 上报ID
     */
    private String reportId;

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
     * 上报类型: SUICIDAL-自杀风险, VIOLENT-暴力倾向, SELF_HARM-自伤行为, OTHER-其他
     */
    private String reportType;

    /**
     * 危机等级: LOW-低, MODERATE-中, HIGH-高, SEVERE-严重
     */
    private String crisisLevel;

    /**
     * 事件描述
     */
    private String incidentDescription;

    /**
     * 风险评估
     */
    private String riskAssessment;

    /**
     * 已采取的紧急措施
     */
    private String immediateActions;

    /**
     * 建议措施
     */
    private String recommendedActions;

    /**
     * 手写危机上报表图片URL
     */
    private String handwrittenReportUrl;

}
