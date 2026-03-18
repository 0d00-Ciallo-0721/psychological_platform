package sasu.platform.mhm.mapper;

import org.apache.ibatis.annotations.Mapper;
import sasu.platform.mhm.pojo.dto.ReferralApprovalQueryDTO;
import sasu.platform.mhm.pojo.vo.ReferralApprovalVO;

import java.util.List;

@Mapper
public interface ReferralMapper {

    /**
     * 转介审批列表
     */
    List<ReferralApprovalVO> selectReferralApprovalList(ReferralApprovalQueryDTO query);

    /**
     * 转介审批通过
     */
    int approveReferral(String id, String reviewerId, String comment, String assignedCounselor);

    /**
     * 转介审批驳回
     */
    int rejectReferral(String id, String reviewerId, String comment);
}


