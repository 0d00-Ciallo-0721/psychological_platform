package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 危机审批操作 DTO（通过/驳回）
 */
@Data
public class CrisisApprovalActionDTO {

    /**
     * 危机上报 ID
     */
    private String id;

    /**
     * 审批意见 / 处理建议
     */
    private String comment;

    /**
     * 前端传的结果：approved / reject
     * 后端可以根据接口（approve/reject）忽略或辅助校验
     */
    private String result;

    /**
     * 是否通知相关人员（前端目前传布尔值，可选）
     */
    private Boolean notify;
}


