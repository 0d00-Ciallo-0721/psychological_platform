package sasu.platform.mhm.pojo.VO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityListItemVO {
    private String activityId;
    private String title;
    private String summary;
    private String coverUrl;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer capacity;
    private Integer joinedCount;

    /**
     * 当前用户是否已报名（登录态下返回）
     */
    private Boolean joined;
}

