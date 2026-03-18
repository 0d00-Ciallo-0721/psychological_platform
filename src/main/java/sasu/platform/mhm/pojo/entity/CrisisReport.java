package sasu.platform.mhm.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 危机上报表
 * @TableName crisis_report
 */
@TableName(value ="crisis_report")
@Data
public class CrisisReport {
    /**
     * 上报ID
     */
    @TableId
    private String report_id;

    /**
     * 预约ID
     */
    private String appointment_id;

    /**
     * 咨询师ID
     */
    private String counselor_id;

    /**
     * 学生ID
     */
    private String student_id;

    /**
     * 上报类型: SUICIDAL-自杀风险, VIOLENT-暴力倾向, SELF_HARM-自伤行为, OTHER-其他
     */
    private String report_type;

    /**
     * 危机等级: LOW-低, MODERATE-中, HIGH-高, SEVERE-严重
     */
    private String crisis_level;

    /**
     * 事件描述
     */
    private String incident_description;

    /**
     * 风险评估
     */
    private String risk_assessment;

    /**
     * 已采取的紧急措施
     */
    private String immediate_actions;

    /**
     * 建议措施
     */
    private String recommended_actions;

    /**
     * 手写危机上报表图片URL
     */
    private String handwritten_report_url;

    /**
     * 上报对象(部门/人员)
     */
    private String reported_to;

    /**
     * 上报状态: PENDING-待处理, REVIEWING-审核中, PROCESSED-已处理, CLOSED-已关闭
     */
    private String report_status;

    /**
     * 审核人ID
     */
    private String reviewer_id;

    /**
     * 审核意见
     */
    private String review_notes;

    /**
     * 跟进计划
     */
    private String follow_up_plan;

    /**
     * 处理结果
     */
    private String resolution_outcome;

    /**
     * 创建时间
     */
    private Date created_at;

    /**
     * 更新时间
     */
    private Date updated_at;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CrisisReport other = (CrisisReport) that;
        return (this.getReport_id() == null ? other.getReport_id() == null : this.getReport_id().equals(other.getReport_id()))
            && (this.getAppointment_id() == null ? other.getAppointment_id() == null : this.getAppointment_id().equals(other.getAppointment_id()))
            && (this.getCounselor_id() == null ? other.getCounselor_id() == null : this.getCounselor_id().equals(other.getCounselor_id()))
            && (this.getStudent_id() == null ? other.getStudent_id() == null : this.getStudent_id().equals(other.getStudent_id()))
            && (this.getReport_type() == null ? other.getReport_type() == null : this.getReport_type().equals(other.getReport_type()))
            && (this.getCrisis_level() == null ? other.getCrisis_level() == null : this.getCrisis_level().equals(other.getCrisis_level()))
            && (this.getIncident_description() == null ? other.getIncident_description() == null : this.getIncident_description().equals(other.getIncident_description()))
            && (this.getRisk_assessment() == null ? other.getRisk_assessment() == null : this.getRisk_assessment().equals(other.getRisk_assessment()))
            && (this.getImmediate_actions() == null ? other.getImmediate_actions() == null : this.getImmediate_actions().equals(other.getImmediate_actions()))
            && (this.getRecommended_actions() == null ? other.getRecommended_actions() == null : this.getRecommended_actions().equals(other.getRecommended_actions()))
            && (this.getHandwritten_report_url() == null ? other.getHandwritten_report_url() == null : this.getHandwritten_report_url().equals(other.getHandwritten_report_url()))
            && (this.getReported_to() == null ? other.getReported_to() == null : this.getReported_to().equals(other.getReported_to()))
            && (this.getReport_status() == null ? other.getReport_status() == null : this.getReport_status().equals(other.getReport_status()))
            && (this.getReviewer_id() == null ? other.getReviewer_id() == null : this.getReviewer_id().equals(other.getReviewer_id()))
            && (this.getReview_notes() == null ? other.getReview_notes() == null : this.getReview_notes().equals(other.getReview_notes()))
            && (this.getFollow_up_plan() == null ? other.getFollow_up_plan() == null : this.getFollow_up_plan().equals(other.getFollow_up_plan()))
            && (this.getResolution_outcome() == null ? other.getResolution_outcome() == null : this.getResolution_outcome().equals(other.getResolution_outcome()))
            && (this.getCreated_at() == null ? other.getCreated_at() == null : this.getCreated_at().equals(other.getCreated_at()))
            && (this.getUpdated_at() == null ? other.getUpdated_at() == null : this.getUpdated_at().equals(other.getUpdated_at()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReport_id() == null) ? 0 : getReport_id().hashCode());
        result = prime * result + ((getAppointment_id() == null) ? 0 : getAppointment_id().hashCode());
        result = prime * result + ((getCounselor_id() == null) ? 0 : getCounselor_id().hashCode());
        result = prime * result + ((getStudent_id() == null) ? 0 : getStudent_id().hashCode());
        result = prime * result + ((getReport_type() == null) ? 0 : getReport_type().hashCode());
        result = prime * result + ((getCrisis_level() == null) ? 0 : getCrisis_level().hashCode());
        result = prime * result + ((getIncident_description() == null) ? 0 : getIncident_description().hashCode());
        result = prime * result + ((getRisk_assessment() == null) ? 0 : getRisk_assessment().hashCode());
        result = prime * result + ((getImmediate_actions() == null) ? 0 : getImmediate_actions().hashCode());
        result = prime * result + ((getRecommended_actions() == null) ? 0 : getRecommended_actions().hashCode());
        result = prime * result + ((getHandwritten_report_url() == null) ? 0 : getHandwritten_report_url().hashCode());
        result = prime * result + ((getReported_to() == null) ? 0 : getReported_to().hashCode());
        result = prime * result + ((getReport_status() == null) ? 0 : getReport_status().hashCode());
        result = prime * result + ((getReviewer_id() == null) ? 0 : getReviewer_id().hashCode());
        result = prime * result + ((getReview_notes() == null) ? 0 : getReview_notes().hashCode());
        result = prime * result + ((getFollow_up_plan() == null) ? 0 : getFollow_up_plan().hashCode());
        result = prime * result + ((getResolution_outcome() == null) ? 0 : getResolution_outcome().hashCode());
        result = prime * result + ((getCreated_at() == null) ? 0 : getCreated_at().hashCode());
        result = prime * result + ((getUpdated_at() == null) ? 0 : getUpdated_at().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", report_id=").append(report_id);
        sb.append(", appointment_id=").append(appointment_id);
        sb.append(", counselor_id=").append(counselor_id);
        sb.append(", student_id=").append(student_id);
        sb.append(", report_type=").append(report_type);
        sb.append(", crisis_level=").append(crisis_level);
        sb.append(", incident_description=").append(incident_description);
        sb.append(", risk_assessment=").append(risk_assessment);
        sb.append(", immediate_actions=").append(immediate_actions);
        sb.append(", recommended_actions=").append(recommended_actions);
        sb.append(", handwritten_report_url=").append(handwritten_report_url);
        sb.append(", reported_to=").append(reported_to);
        sb.append(", report_status=").append(report_status);
        sb.append(", reviewer_id=").append(reviewer_id);
        sb.append(", review_notes=").append(review_notes);
        sb.append(", follow_up_plan=").append(follow_up_plan);
        sb.append(", resolution_outcome=").append(resolution_outcome);
        sb.append(", created_at=").append(created_at);
        sb.append(", updated_at=").append(updated_at);
        sb.append("]");
        return sb.toString();
    }
}