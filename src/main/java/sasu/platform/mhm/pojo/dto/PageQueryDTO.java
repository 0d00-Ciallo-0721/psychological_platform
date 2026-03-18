package sasu.platform.mhm.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 分页查询参数
 */
@Data
public class PageQueryDTO {


    @JsonProperty("id")
    private String id; //内容的id

    @JsonProperty ("type")
    private int type; //栏目类型：1-活动风采 2-通知公告 3-朋辈互助 4-心理百科 5-心理美文

    @JsonProperty("page")
    private Integer page;    //页码

    @JsonProperty("pageSize")
    private Integer pageSize;//每页记录数

    @JsonProperty("keyWords")
    private String keyWords;    //关键词

    @JsonProperty("status")
    private Integer status;//状态

    /**
     * 通用用户ID（部分列表如预约、消息等会用到）
     */
    @JsonProperty("userId")
    private String userId;

    @JsonProperty("org_id")
    private String org_id;//组织id

    @JsonProperty("sortType")
    private int sortType; //排序方式 0-时间降序 1-时间升序

    @JsonProperty("startTime")
    private String startTime; //开始时间

    @JsonProperty("endTime")
    private String endTime; //结束时间

    @JsonProperty("scheduleDate")
    private Integer scheduleDate;//排版日期（星期）

    @JsonProperty("slotDuration")
    private String slotDuration;//单词咨询时间（分钟）
}
