package sasu.platform.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.AppointmentDTO;
import sasu.platform.mhm.pojo.dto.ConsultationScheduleDTO;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.vo.CounselorDetailVO;
import sasu.platform.mhm.service.ConsulateService;


import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/consulate")
public class ConsulateController {
    @Autowired
    private ConsulateService consulateService;
    /**
     * 设置咨询师的预约时间段
     *
     * @param consultationScheduleDTO 咨询预约时间信息DTO
     * @return 返回操作结果，成功时返回设置成功消息
     */
    @PostMapping("/setConsultationTime")
    public R setConsultationTime(@RequestBody ConsultationScheduleDTO consultationScheduleDTO){
        log.info("设置咨询师的预约时间段{}",consultationScheduleDTO);
        consulateService.setConsultationTime(consultationScheduleDTO);
        return R.success("设置成功");
    }
    /**
     * 创建预约
     *
     * @param scheduleId 预约时间ID
     * @param appointmentType 预约类型
     * @param consultationMode 咨询方式
     * @param location 预约地点
     * @param isUrgent 是否紧急预约
     * @param notes 预约备注
     * @return 返回操作结果，包含预约是否成功及相关信息
     */
    @PostMapping("/makeAppointment")
    public R makeAppointment(@RequestParam String scheduleId,
                             @RequestParam String appointmentType,
                             @RequestParam String consultationMode,
                             @RequestParam String location,
                             @RequestParam Integer isUrgent,
                             @RequestParam String  notes){
        AppointmentDTO appointmentDTO = AppointmentDTO.builder()
                .scheduleId(scheduleId)
                .appointmentType(appointmentType)
                .consultationMode(consultationMode)
                .location(location)
                .isUrgent(isUrgent)
                .notes( notes)
                .build();
        log.info("预约咨询师{}",appointmentDTO);
        Map<Boolean,String> result = consulateService.makeAppointment(appointmentDTO);
        return R.success(result);
    }
    /**
     * 获取咨询师列表
     *
     * @param pageQueryDTO 分页查询参数
     * @return 返回咨询师列表的分页结果
     */
    @PostMapping("/list")
    public R list(@RequestBody PageQueryDTO pageQueryDTO){
        PageResult counselorList = consulateService.counselorList(pageQueryDTO);
        return R.success(counselorList);
    }
    @GetMapping("/detail")
    public R getCounselorDetail(@RequestParam String counselorId){
        CounselorDetailVO detailVO = consulateService.getCounselorDetail(counselorId);
        return R.success(detailVO);
    }
    @GetMapping("/schedule")
    public R<PageResult> getScheduleByParams(@RequestBody PageQueryDTO pageQueryDTO){
        PageResult scheduleByParams = consulateService.getScheduleByParams(pageQueryDTO);
        return R.success(scheduleByParams);
    }
}
