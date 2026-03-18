package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.ConsultationScheduleDTO;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.dto.ScheduleCreateDTO;

import java.util.List;
import java.util.Map;

/**
 * 排班服务接口
 */
public interface ScheduleService {

    /**
     * 查询排班列表
     */
    PageResult list(PageQueryDTO query);

    /**
     * 创建排班
     */
    void create(ScheduleCreateDTO dto);

    /**
     * 更新排班
     */
    void update(ConsultationScheduleDTO dto);

    /**
     * 删除排班
     */
    void delete(String scheduleId);

    /**
     * 获取节假日列表
     */
    List<String> getHolidays();

    /**
     * 获取时间段列表
     */
    List<String> getPeriods();

    /**
     * 获取周名称列表
     */
    List<String> getWeekNames();

    /**
     * 获取学期信息
     */
    Map<String, String> getSemester();

    /**
     * 获取周排班模板
     */
    List<Map<String, Object>> getWeekTemplate();
}

