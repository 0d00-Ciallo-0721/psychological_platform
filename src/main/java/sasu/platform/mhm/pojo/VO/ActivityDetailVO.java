package sasu.platform.mhm.pojo.VO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityDetailVO {
    private String activityId;
    private String title;
    private String summary;
    private String content;
    private String coverUrl;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime joinStartTime;
    private LocalDateTime joinEndTime;
    private Integer capacity;
    private Integer joinedCount;
    private String status;

    private Boolean joined;
    private Boolean checkedIn;
}

