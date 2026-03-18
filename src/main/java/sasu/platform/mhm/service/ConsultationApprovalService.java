package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.dto.ConsultationApprovalActionDTO;
import sasu.platform.mhm.pojo.dto.ConsultationApprovalQueryDTO;
import sasu.platform.mhm.pojo.common.PageResult;

public interface ConsultationApprovalService {

    /**
     * 长程咨询申请审批列表
     */
    PageResult list(ConsultationApprovalQueryDTO query);

    /**
     * 审批通过
     */
    void approve(ConsultationApprovalActionDTO dto);

    /**
     * 审批驳回
     */
    void reject(ConsultationApprovalActionDTO dto);
}


