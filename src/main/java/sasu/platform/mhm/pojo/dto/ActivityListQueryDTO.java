package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 团体活动列表查询参数（GET接口用RequestParam也可；这里用于Service/Mapper传参）
 */
@Data
public class ActivityListQueryDTO {
    private Integer page;
    private Integer pageSize;
    private String keyword;
    private String status; // 默认PUBLISHED
    private String startTime; // yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss（按前端传参约定）
    private String endTime;
}

