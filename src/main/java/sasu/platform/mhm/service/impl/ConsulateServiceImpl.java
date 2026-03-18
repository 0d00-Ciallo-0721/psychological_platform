package sasu.platform.mhm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sasu.platform.mhm.mapper.ConsulateMapper;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.AppointmentDTO;
import sasu.platform.mhm.pojo.dto.ConsultationScheduleDTO;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.entity.Appointment;
import sasu.platform.mhm.pojo.entity.ConsultationSchedule;
import sasu.platform.mhm.pojo.entity.UserDetailsImpl;
import sasu.platform.mhm.pojo.vo.CounselorDetailVO;
import sasu.platform.mhm.pojo.vo.CounselorVO;
import sasu.platform.mhm.service.ConsulateService;
import sasu.platform.mhm.util.ConsultationScheduleBuilderConverter;

import java.util.Map;

@Slf4j
@Service
public class ConsulateServiceImpl implements ConsulateService {
    @Autowired
    private ConsultationScheduleBuilderConverter consultationScheduleBuilderConverter;
    @Autowired
    private ConsulateMapper consulateMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean setConsultationTime(ConsultationScheduleDTO consultationScheduleDTO) {
        //获取当前用户的个人信息SecurityContextHolder
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userDetails.getUser().getUserId();
        //将ConsultationScheduleDTO转换为ConsultationSchedule
        ConsultationSchedule consultationSchedule = consultationScheduleBuilderConverter.toEntity(consultationScheduleDTO);
        consultationSchedule.setUserId(userId);
        //将ConsultationSchedule保存到数据库中
        boolean result = consulateMapper.insertConsultationSchedule(consultationSchedule);
        log.info("用户id为：{}", userId);
        return true;
    }

    @Override
    public Map<Boolean, String> makeAppointment(AppointmentDTO appointmentDTO) {
        //查询所选预约时间段是否被占有
        Object o = redisTemplate.opsForValue().get("appointment:" + appointmentDTO.getScheduleId());
        if (o != null) {
            return Map.of(false, "所选预约时间段已被占有");
        }
        //获取用户的个人信息
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        redisTemplate.opsForValue().set("appointment:" + appointmentDTO.getScheduleId(),userDetails.getUser().getUserId());
        //将预约信息存储到数据库中
        Appointment appointment = Appointment.builder()
                .userId(userDetails.getUser().getUserId())
                .scheduleId(appointmentDTO.getScheduleId())
                .appointmentType(appointmentDTO.getAppointmentType())
                .consultationMode(appointmentDTO.getConsultationMode())
                .location(appointmentDTO.getLocation())
                .appointmentStatus("待处理")
                .isUrgent(appointmentDTO.getIsUrgent())
                .notes(appointmentDTO.getNotes())
                .build();
        //获取预约时间段的相关信息
        ConsultationSchedule consultationSchedule = consulateMapper.getScheduleInfoByScheduleId(appointmentDTO.getScheduleId());
        boolean result = consulateMapper.insertAppointment(appointment);
        return Map.of(result, result ? "预约成功" : "预约失败");
    }

    @Override
    public PageResult getScheduleByParams(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<ConsultationSchedule> appointmentVOS = consulateMapper.getScheduleByParams(pageQueryDTO);
        return new PageResult(appointmentVOS.getTotal(), appointmentVOS.getResult());
    }

    @Override
    public PageResult counselorList(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<CounselorVO> counselorVOS = consulateMapper.counselorList(pageQueryDTO);
        return new PageResult(counselorVOS.getTotal(), counselorVOS.getResult());
    }

    @Override
    public CounselorDetailVO getCounselorDetail(String counselorId) {
        CounselorDetailVO detailVO = consulateMapper.getCounselorDetail(counselorId);
        //获取咨询师的咨询时间段安排
        detailVO.setConsultationSchedule(consulateMapper.getScheduleByCounselorId(counselorId));
        return detailVO;
    }
}
