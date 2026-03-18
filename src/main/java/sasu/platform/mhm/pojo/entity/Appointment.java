package sasu.platform.mhm.pojo.entity;

import lombok.Builder;
import lombok.Data;
import sasu.platform.mhm.util.ConsultationScheduleBuilderConverter;

@Data
@Builder
public class Appointment {
    private String userId;
    private String scheduleId;
    private String appointmentType;//咨询类型: INDIVIDUAL-个体, GROUP-团体, EMERGENCY-紧急
    private String consultationMode;//咨询方式: OFFLINE-线下, ONLINE-线上
    private String location;
    private String appointmentStatus;//预约状态: PENDING-待确认, CONFIRMED-已确认, COMPLETED-已完成, CANCELLED-已取消, NO_SHOW-爽约
    private String cancellationReason;//取消原因
    private String cancelledBy;//取消人
    private int isUrgent;//是否紧急预约: 0-否, 1-是
    private String notes;//备注
    private int hasVisitedBefore;//是否 previously visited: 0-否, 1-是
    private int visitCount;//访问次数
}
