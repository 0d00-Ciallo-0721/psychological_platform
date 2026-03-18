package sasu.platform.mhm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sasu.platform.mhm.mapper.ConsulateMapper;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.AppointmentDTO;
import sasu.platform.mhm.pojo.dto.AppointmentStatusUpdateDTO;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.entity.UserDetailsImpl;
import sasu.platform.mhm.pojo.vo.AppointmentDetailVO;
import sasu.platform.mhm.service.AppointmentService;
import sasu.platform.mhm.service.ConsulateService;

import java.util.Map;

/**
 * 预约服务实现
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private ConsulateService consulateService;

    @Autowired
    private ConsulateMapper consulateMapper;

    /**
     * 获取当前用户ID
     */
    private String currentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            return userDetails.getUser().getUserId();
        }
        return null;
    }

    @Override
    public Map<Boolean, String> createAppointment(AppointmentDTO dto) {
        return consulateService.makeAppointment(dto);
    }

    @Override
    public PageResult list(PageQueryDTO query) {
        if (query == null) {
            query = new PageQueryDTO();
        }
        if (query.getPage() == null || query.getPage() <= 0) {
            query.setPage(1);
        }
        if (query.getPageSize() == null || query.getPageSize() <= 0) {
            query.setPageSize(10);
        }

        PageHelper.startPage(query.getPage(), query.getPageSize());
        Page<AppointmentDetailVO> page = consulateMapper.listAppointments(query);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public AppointmentDetailVO detail(String appointmentId) {
        if (!StringUtils.hasText(appointmentId)) {
            throw new IllegalArgumentException("appointmentId不能为空");
        }
        AppointmentDetailVO detail = consulateMapper.getAppointmentById(appointmentId);
        if (detail == null) {
            throw new IllegalArgumentException("预约不存在");
        }
        return detail;
    }

    @Override
    public void cancel(AppointmentStatusUpdateDTO dto) {
        if (!StringUtils.hasText(dto.getId())) {
            throw new IllegalArgumentException("预约ID不能为空");
        }
        String userId = currentUserId();
        String cancelledBy = "STUDENT"; // 默认学生取消，可根据角色判断

        int result = consulateMapper.updateAppointmentStatus(
            dto.getId(),
            "CANCELLED",
            dto.getReason(),
            cancelledBy
        );
        if (result <= 0) {
            throw new RuntimeException("取消预约失败");
        }
    }

    @Override
    public void confirm(AppointmentStatusUpdateDTO dto) {
        if (!StringUtils.hasText(dto.getId())) {
            throw new IllegalArgumentException("预约ID不能为空");
        }
        int result = consulateMapper.updateAppointmentStatus(
            dto.getId(),
            "CONFIRMED",
            null,
            null
        );
        if (result <= 0) {
            throw new RuntimeException("确认预约失败");
        }
    }

    @Override
    public void reject(AppointmentStatusUpdateDTO dto) {
        if (!StringUtils.hasText(dto.getId())) {
            throw new IllegalArgumentException("预约ID不能为空");
        }
        String cancelledBy = "COUNSELOR";
        int result = consulateMapper.updateAppointmentStatus(
            dto.getId(),
            "REJECTED",
            dto.getReason(),
            cancelledBy
        );
        if (result <= 0) {
            throw new RuntimeException("拒绝预约失败");
        }
    }

    @Override
    public void markNoShow(AppointmentStatusUpdateDTO dto) {
        if (!StringUtils.hasText(dto.getId())) {
            throw new IllegalArgumentException("预约ID不能为空");
        }
        int result = consulateMapper.updateAppointmentStatus(
            dto.getId(),
            "NO_SHOW",
            null,
            "COUNSELOR"
        );
        if (result <= 0) {
            throw new RuntimeException("标记爽约失败");
        }
    }

    @Override
    public void complete(AppointmentStatusUpdateDTO dto) {
        if (!StringUtils.hasText(dto.getId())) {
            throw new IllegalArgumentException("预约ID不能为空");
        }
        int result = consulateMapper.updateAppointmentStatus(
            dto.getId(),
            "COMPLETED",
            null,
            null
        );
        if (result <= 0) {
            throw new RuntimeException("完成预约失败");
        }
    }
}

