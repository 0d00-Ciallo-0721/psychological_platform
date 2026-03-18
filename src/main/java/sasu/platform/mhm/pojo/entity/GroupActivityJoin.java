package sasu.platform.mhm.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GroupActivityJoin {
    private String joinId;
    private String activityId;
    private String userId;
    private String joinStatus;
    private LocalDateTime joinedAt;
    private LocalDateTime cancelledAt;
    private Integer checkinStatus;
    private LocalDateTime checkedInAt;
    private String collegeSnapshot;
    private String classSnapshot;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

