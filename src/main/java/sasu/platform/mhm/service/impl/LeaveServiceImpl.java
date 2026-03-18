package sasu.platform.mhm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sasu.platform.mhm.mapper.LeaveMapper;
import sasu.platform.mhm.pojo.dto.LeaveApplyDTO;
import sasu.platform.mhm.pojo.dto.LeaveApprovalDTO;
import sasu.platform.mhm.pojo.dto.LeaveListQueryDTO;
import sasu.platform.mhm.pojo.entity.LeaveRequest;
import sasu.platform.mhm.pojo.vo.LeaveVO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.service.LeaveService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveMapper leaveMapper;

    @Override
    public PageResult list(LeaveListQueryDTO query) {
        if (query.getPage() == null || query.getPageSize() == null) {
            query.setPage(1);
            query.setPageSize(10);
        }

        // 学生端默认查询当前学生
        if (query.getStudentId() == null || query.getStudentId().isEmpty()) {
            String currentUserId = null;
            query.setStudentId(currentUserId);
        }

        PageHelper.startPage(query.getPage(), query.getPageSize());
        Page<LeaveVO> page = (Page<LeaveVO>) leaveMapper.selectList(query);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    @Transactional
    public void apply(LeaveApplyDTO dto) {
        String currentUserId = null;

        LeaveRequest request = new LeaveRequest();
        request.setLeaveId(UUID.randomUUID().toString());
        request.setStudentId(currentUserId);
        request.setLeaveType(dto.getLeaveType());
        request.setReason(dto.getReason());
        request.setStatus("PENDING");

        LocalDateTime now = LocalDateTime.now();
        request.setCreatedAt(now);
        request.setUpdatedAt(now);

        // 这里简单解析时间字符串，建议前端统一传 ISO_LOCAL_DATE_TIME
        request.setStartTime(LocalDateTime.parse(dto.getStartTime()));
        request.setEndTime(LocalDateTime.parse(dto.getEndTime()));

        leaveMapper.insert(request);
    }

    @Override
    @Transactional
    public void cancel(String leaveId, String reason) {
        LeaveRequest request = leaveMapper.selectById(leaveId);
        if (request == null) {
            return;
        }
        request.setStatus("CANCELLED");
        request.setCancelReason(reason);
        request.setUpdatedAt(LocalDateTime.now());
        leaveMapper.update(request);
    }

    @Override
    @Transactional
    public void approve(LeaveApprovalDTO dto) {
        LeaveRequest request = leaveMapper.selectById(dto.getLeaveId());
        if (request == null) {
            return;
        }
        String currentUserId = null;
        request.setStatus("APPROVED");
        request.setApproveReason(dto.getReason());
        request.setCounselorId(currentUserId);
        request.setUpdatedAt(LocalDateTime.now());
        leaveMapper.update(request);
    }

    @Override
    @Transactional
    public void reject(LeaveApprovalDTO dto) {
        LeaveRequest request = leaveMapper.selectById(dto.getLeaveId());
        if (request == null) {
            return;
        }
        String currentUserId = null;
        request.setStatus("REJECTED");
        request.setApproveReason(dto.getReason());
        request.setCounselorId(currentUserId);
        request.setUpdatedAt(LocalDateTime.now());
        leaveMapper.update(request);
    }
}


