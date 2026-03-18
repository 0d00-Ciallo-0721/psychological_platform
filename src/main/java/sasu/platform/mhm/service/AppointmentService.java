package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.AppointmentDTO;
import sasu.platform.mhm.pojo.dto.AppointmentStatusUpdateDTO;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.vo.AppointmentDetailVO;

import java.util.Map;

/**
 * 预约服务接口
 */
public interface AppointmentService {

    /**
     * 创建预约
     */
    Map<Boolean, String> createAppointment(AppointmentDTO dto);

    /**
     * 查询预约列表
     */
    PageResult list(PageQueryDTO query);

    /**
     * 查询预约详情
     */
    AppointmentDetailVO detail(String appointmentId);

    /**
     * 取消预约
     */
    void cancel(AppointmentStatusUpdateDTO dto);

    /**
     * 确认预约
     */
    void confirm(AppointmentStatusUpdateDTO dto);

    /**
     * 拒绝预约
     */
    void reject(AppointmentStatusUpdateDTO dto);

    /**
     * 标记爽约
     */
    void markNoShow(AppointmentStatusUpdateDTO dto);

    /**
     * 完成预约
     */
    void complete(AppointmentStatusUpdateDTO dto);
}

