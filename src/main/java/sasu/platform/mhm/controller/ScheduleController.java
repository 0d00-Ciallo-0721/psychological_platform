package sasu.platform.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.ConsultationScheduleDTO;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.dto.ScheduleCreateDTO;
import sasu.platform.mhm.service.ConsulateService;
import sasu.platform.mhm.service.ScheduleService;

import java.util.List;
import java.util.Map;

/**
 * 排班相关接口
 *
 * 对应前端：/api/schedule/*
 * - list: 获取排班/可预约时间
 * - update: 更新排班（当前作为占位，不做具体字段修改）
 */
@Slf4j
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ConsulateService consulateService;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * 排班列表
     *
     * 对应前端：GET /api/schedule/list
     */
    @GetMapping("/list")
    public R<PageResult> list(PageQueryDTO queryDTO) {
        log.info("查询排班列表: {}", queryDTO);
        PageResult pageResult = consulateService.getScheduleByParams(queryDTO);
        return R.success(pageResult);
    }

    /**
     * 设置 / 更新排班
     *
     * 对应前端：PUT /api/schedule/update
     * 目前直接复用 setConsultationTime 能力。
     */
    @PutMapping("/update")
    public R<String> update(@RequestBody ConsultationScheduleDTO dto) {
        log.info("更新排班: {}", dto);
        scheduleService.update(dto);
        return R.success("更新成功");
    }

    /**
     * 创建排班
     */
    @PostMapping("/create")
    public R<String> create(@RequestBody ScheduleCreateDTO dto) {
        log.info("创建排班: {}", dto);
        scheduleService.create(dto);
        return R.success("创建成功");
    }

    /**
     * 删除排班
     */
    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam String id) {
        log.info("删除排班: {}", id);
        scheduleService.delete(id);
        return R.success("删除成功");
    }

    /**
     * 获取节假日列表
     */
    @GetMapping("/holidays")
    public R<List<String>> getHolidays() {
        log.info("获取节假日列表");
        List<String> holidays = scheduleService.getHolidays();
        return R.success(holidays);
    }

    /**
     * 获取时间段列表
     */
    @GetMapping("/periods")
    public R<List<String>> getPeriods() {
        log.info("获取时间段列表");
        List<String> periods = scheduleService.getPeriods();
        return R.success(periods);
    }

    /**
     * 获取周名称列表
     */
    @GetMapping("/week-names")
    public R<List<String>> getWeekNames() {
        log.info("获取周名称列表");
        List<String> weekNames = scheduleService.getWeekNames();
        return R.success(weekNames);
    }

    /**
     * 获取学期信息
     */
    @GetMapping("/semester")
    public R<Map<String, String>> getSemester() {
        log.info("获取学期信息");
        Map<String, String> semester = scheduleService.getSemester();
        return R.success(semester);
    }

    /**
     * 获取周排班模板
     */
    @GetMapping("/template")
    public R<List<Map<String, Object>>> getWeekTemplate() {
        log.info("获取周排班模板");
        List<Map<String, Object>> template = scheduleService.getWeekTemplate();
        return R.success(template);
    }
}


