package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 测评方案列表查询 DTO
 */
@Data
public class AssessmentListQueryDTO {

    private Integer page;

    private Integer pageSize;

    private String keyword;

    /**
     * 状态过滤：可选
     */
    private Integer status;
}

