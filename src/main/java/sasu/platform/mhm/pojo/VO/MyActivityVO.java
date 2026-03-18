package sasu.platform.mhm.pojo.VO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyActivityVO {
    private String activityId;
    private String title;
    private String coverUrl;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String joinStatus;
    private Integer checkinStatus;
    private LocalDateTime joinedAt;
    private LocalDateTime checkedInAt;

    private String collegeSnapshot;
    private String classSnapshot;
}

