package sasu.platform.mhm.pojo.vo;

import lombok.Data;

@Data
public class StatsCollegeItemVO {
    private String collegeId;
    private String collegeName;
    private long studentCount;
    private long appointmentCount;
    private long crisisCount;
}


