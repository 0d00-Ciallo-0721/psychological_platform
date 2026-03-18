package sasu.platform.mhm.pojo.entity;

import lombok.Data;

import java.sql.Time;
import java.util.List;

@Data
public class ConsultationSchedule {
    private String userId;
    private List<ConsultationScheduleDate> consultationScheduleDates;
    @Data
    public static class ConsultationScheduleDate {
        private int schedule_date;
        private List<ConsultationTime> consultationTime;
    }
    @Data
    public static class ConsultationTime{
        private Time startTime;//开始时间
        private Time endTime;//结束时间
        private String slot_duration;//单次咨询时长
        private int available_slots;//剩余可预约数
        private int status;//状状态: 0-停用, 1-启用, 2-已满
        private String schedule_type;//排班类型: REGULAR-常规, SPECIAL-特殊, HOLIDAY-节假日
        private String reservationLocation;//预约地点 线上则直接填写线上咨询 线下直接填写咨询地点（校区）
    }

}
