package sasu.platform.mhm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sasu.platform.mhm.mapper.ConsultationApprovalMapper;
import sasu.platform.mhm.pojo.dto.ConsultationApprovalActionDTO;
import sasu.platform.mhm.pojo.dto.ConsultationApprovalQueryDTO;
import sasu.platform.mhm.pojo.vo.ConsultationApprovalVO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.service.ConsultationApprovalService;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsultationApprovalServiceImpl implements ConsultationApprovalService {

    private final ConsultationApprovalMapper consultationApprovalMapper;

    @Override
    public PageResult list(ConsultationApprovalQueryDTO query) {
        if (query.getPage() == null || query.getPageSize() == null) {
            query.setPage(1);
            query.setPageSize(20);
        }
        PageHelper.startPage(query.getPage(), query.getPageSize());
        Page<ConsultationApprovalVO> page =
                (Page<ConsultationApprovalVO>) consultationApprovalMapper.selectConsultationApprovalList(query);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    @Transactional
    public void approve(ConsultationApprovalActionDTO dto) {
        log.info("长程咨询申请审批通过, id={}", dto.getId());
        LocalDate start = dto.getStartDate() != null ? LocalDate.parse(dto.getStartDate()) : null;
        LocalDate end = dto.getEndDate() != null ? LocalDate.parse(dto.getEndDate()) : null;
        consultationApprovalMapper.approve(
                dto.getId(),
                null,
                dto.getComment(),
                dto.getApprovedSessions(),
                dto.getApprovedFrequency(),
                start,
                end
        );
    }

    @Override
    @Transactional
    public void reject(ConsultationApprovalActionDTO dto) {
        log.info("长程咨询申请审批驳回, id={}", dto.getId());
        consultationApprovalMapper.reject(
                dto.getId(),
                null,
                dto.getComment()
        );
    }
}


