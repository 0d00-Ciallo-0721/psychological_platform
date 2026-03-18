package sasu.platform.mhm.service.impl;

import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sasu.platform.mhm.mapper.ConsulateMapper;
import sasu.platform.mhm.mapper.ConfigMapper;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.ConsultationScheduleDTO;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.dto.ScheduleCreateDTO;
import sasu.platform.mhm.pojo.entity.CounselorSchedule;
import sasu.platform.mhm.pojo.entity.SystemConfig;
import sasu.platform.mhm.pojo.entity.UserDetailsImpl;
import sasu.platform.mhm.service.ConsulateService;
import sasu.platform.mhm.service.ScheduleService;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 排班服务实现
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ConsulateService consulateService;

    @Autowired
    private ConsulateMapper consulateMapper;

    @Autowired
    private ConfigMapper configMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

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
    public PageResult list(PageQueryDTO query) {
        return consulateService.getScheduleByParams(query);
    }

    @Override
    public void create(ScheduleCreateDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("排班信息不能为空");
        }
        if (!StringUtils.hasText(dto.getCounselorId())) {
            throw new IllegalArgumentException("咨询师ID不能为空");
        }
        if (dto.getScheduleDate() == null) {
            throw new IllegalArgumentException("排班日期不能为空");
        }
        if (dto.getStartTime() == null || dto.getEndTime() == null) {
            throw new IllegalArgumentException("开始时间和结束时间不能为空");
        }

        String userId = currentUserId();
        CounselorSchedule schedule = new CounselorSchedule();
        schedule.setScheduleId(IdUtil.randomUUID());
        schedule.setCounselorId(dto.getCounselorId());
        schedule.setScheduleDate(dto.getScheduleDate());
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());
        schedule.setSlotDuration(StringUtils.hasText(dto.getSlotDuration()) ? dto.getSlotDuration() : "50");
        schedule.setAvailableSlots(dto.getAvailableSlots() != null ? dto.getAvailableSlots() : 1);
        schedule.setScheduleType(StringUtils.hasText(dto.getScheduleType()) ? dto.getScheduleType() : "REGULAR");
        schedule.setStatus(dto.getStatus() != null ? dto.getStatus() : 1);
        schedule.setReservationLocation(dto.getReservationLocation());
        schedule.setCreatedBy(userId);

        int result = consulateMapper.insertSingleSchedule(schedule);
        if (result <= 0) {
            throw new RuntimeException("创建排班失败");
        }
    }

    @Override
    public void update(ConsultationScheduleDTO dto) {
        consulateService.setConsultationTime(dto);
    }

    @Override
    public void delete(String scheduleId) {
        if (!StringUtils.hasText(scheduleId)) {
            throw new IllegalArgumentException("排班ID不能为空");
        }
        int result = consulateMapper.deleteSchedule(scheduleId);
        if (result <= 0) {
            throw new RuntimeException("删除排班失败");
        }
    }

    @Override
    public List<String> getHolidays() {
        SystemConfig config = configMapper.selectByType("SCHEDULE_HOLIDAYS");
        if (config == null || !StringUtils.hasText(config.getConfigValue())) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(config.getConfigValue(), new TypeReference<List<String>>() {});
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> getPeriods() {
        SystemConfig config = configMapper.selectByType("SCHEDULE_PERIODS");
        if (config == null || !StringUtils.hasText(config.getConfigValue())) {
            // 返回默认时间段
            return Arrays.asList(
                "09:00-09:50",
                "09:50-10:40",
                "10:40-11:30",
                "11:30-12:20",
                "15:00-15:50",
                "15:50-16:40",
                "16:40-17:30"
            );
        }
        try {
            return objectMapper.readValue(config.getConfigValue(), new TypeReference<List<String>>() {});
        } catch (Exception e) {
            return Arrays.asList("09:00-09:50", "09:50-10:40", "10:40-11:30");
        }
    }

    @Override
    public List<String> getWeekNames() {
        return Arrays.asList("周一", "周二", "周三", "周四", "周五", "周六", "周日");
    }

    @Override
    public Map<String, String> getSemester() {
        SystemConfig config = configMapper.selectByType("SCHEDULE_SEMESTER");
        if (config == null || !StringUtils.hasText(config.getConfigValue())) {
            Map<String, String> defaultSemester = new HashMap<>();
            defaultSemester.put("start", "2026-03-01");
            defaultSemester.put("end", "2026-07-10");
            return defaultSemester;
        }
        try {
            return objectMapper.readValue(config.getConfigValue(), new TypeReference<Map<String, String>>() {});
        } catch (Exception e) {
            Map<String, String> defaultSemester = new HashMap<>();
            defaultSemester.put("start", "2026-03-01");
            defaultSemester.put("end", "2026-07-10");
            return defaultSemester;
        }
    }

    @Override
    public List<Map<String, Object>> getWeekTemplate() {
        // 从排班表中查询当前周的排班作为模板
        // 这里简化处理，返回空列表，实际应该查询数据库
        return new ArrayList<>();
    }
}

