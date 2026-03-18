package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.dto.LeaveApplyDTO;
import sasu.platform.mhm.pojo.dto.LeaveApprovalDTO;
import sasu.platform.mhm.pojo.dto.LeaveListQueryDTO;
import sasu.platform.mhm.pojo.vo.LeaveVO;
import sasu.platform.mhm.pojo.common.PageResult;

public interface LeaveService {

    /**
     * 请假列表
     */
    PageResult list(LeaveListQueryDTO query);

    /**
     * 申请请假
     */
    void apply(LeaveApplyDTO dto);

    /**
     * 取消请假
     */
    void cancel(String leaveId, String reason);

    /**
     * 审批通过
     */
    void approve(LeaveApprovalDTO dto);

    /**
     * 审批拒绝
     */
    void reject(LeaveApprovalDTO dto);
}


