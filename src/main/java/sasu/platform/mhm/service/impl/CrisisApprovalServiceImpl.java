package sasu.platform.mhm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sasu.platform.mhm.mapper.CrisisReportMapper;
import sasu.platform.mhm.pojo.dto.CrisisApprovalActionDTO;
import sasu.platform.mhm.pojo.dto.CrisisApprovalQueryDTO;
import sasu.platform.mhm.pojo.vo.CrisisApprovalVO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.service.CrisisApprovalService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CrisisApprovalServiceImpl implements CrisisApprovalService {

    private final CrisisReportMapper crisisReportMapper;

    @Override
    public PageResult list(CrisisApprovalQueryDTO query) {
        if (query.getPage() == null || query.getPageSize() == null) {
            query.setPage(1);
            query.setPageSize(20);
        }
        PageHelper.startPage(query.getPage(), query.getPageSize());
        Page<CrisisApprovalVO> page =
                (Page<CrisisApprovalVO>) crisisReportMapper.selectCrisisApprovalList(query);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    @Transactional
    public void approve(CrisisApprovalActionDTO dto) {
        String reviewerId = null;
        log.info("危机审批通过, id={}, reviewerId={}, comment={}", dto.getId(), reviewerId, dto.getComment());
        crisisReportMapper.approveCrisis(dto.getId(), reviewerId, dto.getComment());
    }

    @Override
    @Transactional
    public void reject(CrisisApprovalActionDTO dto) {
        String reviewerId = null;
        log.info("危机审批驳回, id={}, reviewerId={}, comment={}", dto.getId(), reviewerId, dto.getComment());
        crisisReportMapper.rejectCrisis(dto.getId(), reviewerId, dto.getComment());
    }
}


