package sasu.platform.mhm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sasu.platform.mhm.mapper.ReferralMapper;
import sasu.platform.mhm.pojo.dto.ReferralApprovalActionDTO;
import sasu.platform.mhm.pojo.dto.ReferralApprovalQueryDTO;
import sasu.platform.mhm.pojo.vo.ReferralApprovalVO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.service.ReferralApprovalService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReferralApprovalServiceImpl implements ReferralApprovalService {

    private final ReferralMapper referralMapper;

    @Override
    public PageResult list(ReferralApprovalQueryDTO query) {
        if (query.getPage() == null || query.getPageSize() == null) {
            query.setPage(1);
            query.setPageSize(20);
        }
        PageHelper.startPage(query.getPage(), query.getPageSize());
        Page<ReferralApprovalVO> page =
                (Page<ReferralApprovalVO>) referralMapper.selectReferralApprovalList(query);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    @Transactional
    public void approve(ReferralApprovalActionDTO dto) {
        log.info("转介审批通过, id={}, comment={}, assignedCounselor={}",
                dto.getId(), dto.getComment(), dto.getAssignedCounselor());
        referralMapper.approveReferral(dto.getId(), null, dto.getComment(), dto.getAssignedCounselor());
    }

    @Override
    @Transactional
    public void reject(ReferralApprovalActionDTO dto) {
        log.info("转介审批驳回, id={}, comment={}", dto.getId(), dto.getComment());
        referralMapper.rejectReferral(dto.getId(), null, dto.getComment());
    }
}


