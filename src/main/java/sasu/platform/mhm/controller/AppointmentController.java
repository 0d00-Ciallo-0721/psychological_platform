package sasu.platform.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.AppointmentDTO;
import sasu.platform.mhm.pojo.dto.AppointmentStatusUpdateDTO;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.vo.AppointmentDetailVO;
import sasu.platform.mhm.service.AppointmentService;
import sasu.platform.mhm.service.ConsulateService;

import java.util.List;
import java.util.Map;

/**
 * 预约相关接口
 *
 * 说明：
 * - 为了快速对接前端，目前基于现有 ConsulateService 做一个最小可用实现
 * - 这里主要提供 /api/appointment/submit 和 /api/appointment/my-list
 * - 后续可以根据需要细化状态流转（cancel/confirm/reject 等）
 */
@Slf4j
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private ConsulateService consulateService;

    @Autowired
    private AppointmentService appointmentService;

    /**
     * 创建预约（提交）
     *
     * 对应前端：POST /api/appointment/submit
     * 暂时复用 ConsulateService.makeAppointment
     */
    @PostMapping("/submit")
    public R<Map<Boolean, String>> submit(@RequestBody AppointmentDTO appointmentDTO) {
        log.info("创建预约: {}", appointmentDTO);
        Map<Boolean, String> result = consulateService.makeAppointment(appointmentDTO);
        return R.success(result);
    }

    /**
     * 我的预约列表
     *
     * 对应前端：GET /api/appointment/my-list
     * 这里暂时通过 PageQueryDTO 传入 userId 等查询条件，由 ConsulateService 统一处理。
     */
    @GetMapping("/my-list")
    public R<PageResult> myList(PageQueryDTO queryDTO) {
        log.info("查询我的预约列表: {}", queryDTO);
        PageResult pageResult = consulateService.getScheduleByParams(queryDTO);
        return R.success(pageResult);
    }

    /**
     * 预约详情
     */
    @GetMapping("/detail")
    public R<AppointmentDetailVO> detail(@RequestParam String id) {
        log.info("查询预约详情: {}", id);
        AppointmentDetailVO detail = appointmentService.detail(id);
        return R.success(detail);
    }

    /**
     * 预约列表（管理端）
     */
    @GetMapping("/list")
    public R<PageResult> list(PageQueryDTO queryDTO) {
        log.info("查询预约列表: {}", queryDTO);
        PageResult pageResult = appointmentService.list(queryDTO);
        return R.success(pageResult);
    }

    /**
     * 取消预约
     */
    @PostMapping("/cancel")
    public R<String> cancel(@RequestBody AppointmentStatusUpdateDTO dto) {
        log.info("取消预约: {}", dto);
        appointmentService.cancel(dto);
        return R.success("取消成功");
    }

    /**
     * 确认预约
     */
    @PostMapping("/confirm")
    public R<String> confirm(@RequestBody AppointmentStatusUpdateDTO dto) {
        log.info("确认预约: {}", dto);
        appointmentService.confirm(dto);
        return R.success("确认成功");
    }

    /**
     * 拒绝预约
     */
    @PostMapping("/reject")
    public R<String> reject(@RequestBody AppointmentStatusUpdateDTO dto) {
        log.info("拒绝预约: {}", dto);
        appointmentService.reject(dto);
        return R.success("已拒绝");
    }

    /**
     * 标记爽约
     */
    @PostMapping("/mark-no-show")
    public R<String> markNoShow(@RequestBody AppointmentStatusUpdateDTO dto) {
        log.info("标记爽约: {}", dto);
        appointmentService.markNoShow(dto);
        return R.success("已标记爽约");
    }

    /**
     * 获取前测量表配置
     */
    @GetMapping("/pre-assessment")
    public R<Object> getPreAssessmentConfig() {
        log.info("获取前测量表配置");
        // 这里可以调用ConfigService获取配置，或者返回默认配置
        return R.success(Map.of("scales", List.of()));
    }

    /**
     * 获取知情同意书内容
     */
    @GetMapping("/consent")
    public R<Object> getConsentContent() {
        log.info("获取知情同意书内容");
        // 这里可以调用ConfigService获取配置，或者返回默认内容
        return R.success(Map.of("content", "知情同意书内容", "enabled", true, "force", true));
    }
}


