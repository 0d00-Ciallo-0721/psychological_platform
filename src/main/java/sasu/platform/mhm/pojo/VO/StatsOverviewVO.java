package sasu.platform.mhm.pojo.vo;

import lombok.Data;

@Data
public class StatsOverviewVO {
    private long totalStudents;
    private long activeStudents;
    private long totalAppointments;
    private long pendingAppointments;
    private long crisisReports;
}


