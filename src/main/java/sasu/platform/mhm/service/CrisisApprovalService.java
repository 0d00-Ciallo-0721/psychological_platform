package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.dto.CrisisApprovalActionDTO;
import sasu.platform.mhm.pojo.dto.CrisisApprovalQueryDTO;
import sasu.platform.mhm.pojo.vo.CrisisApprovalVO;
import sasu.platform.mhm.pojo.common.PageResult;

public interface CrisisApprovalService {

    /**
     * 危机审批列表
     */
    PageResult list(CrisisApprovalQueryDTO query);

    /**
     * 危机审批通过
     */
    void approve(CrisisApprovalActionDTO dto);

    /**
     * 危机审批驳回
     */
    void reject(CrisisApprovalActionDTO dto);
}


