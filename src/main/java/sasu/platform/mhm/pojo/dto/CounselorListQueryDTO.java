package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 辅导员列表查询 DTO（领导端）
 */
@Data
public class CounselorListQueryDTO extends PageQueryDTO {

    /**
     * 范围：all / college
     */
    private String scope;
}


