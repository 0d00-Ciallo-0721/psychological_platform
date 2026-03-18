package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.dto.ReferralApprovalActionDTO;
import sasu.platform.mhm.pojo.dto.ReferralApprovalQueryDTO;
import sasu.platform.mhm.pojo.common.PageResult;

public interface ReferralApprovalService {

    /**
     * 转介审批列表
     */
    PageResult list(ReferralApprovalQueryDTO query);

    /**
     * 转介审批通过
     */
    void approve(ReferralApprovalActionDTO dto);

    /**
     * 转介审批驳回
     */
    void reject(ReferralApprovalActionDTO dto);
}


