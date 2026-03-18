package sasu.platform.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.RecordCreateDTO;
import sasu.platform.mhm.pojo.dto.RecordListQueryDTO;
import sasu.platform.mhm.pojo.dto.RecordUpdateDTO;
import sasu.platform.mhm.pojo.vo.RecordVO;
import sasu.platform.mhm.service.RecordService;

/**
 * 咨询记录控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    /**
     * 咨询记录列表
     */
    @GetMapping("/list")
    public R<PageResult> list(RecordListQueryDTO query) {
        log.info("查询咨询记录列表: {}", query);
        PageResult result = recordService.list(query);
        return R.success(result);
    }

    /**
     * 咨询记录详情
     */
    @GetMapping("/detail")
    public R<RecordVO> detail(@RequestParam String id) {
        log.info("查询咨询记录详情: {}", id);
        RecordVO record = recordService.detail(id);
        return R.success(record);
    }

    /**
     * 根据预约ID查询记录
     */
    @GetMapping("/by-appointment")
    public R<RecordVO> getByAppointmentId(@RequestParam String appointmentId) {
        log.info("根据预约ID查询记录: {}", appointmentId);
        RecordVO record = recordService.getByAppointmentId(appointmentId);
        return R.success(record);
    }

    /**
     * 创建咨询记录
     */
    @PostMapping("/create")
    public R<RecordVO> create(@ModelAttribute RecordCreateDTO dto) {
        log.info("创建咨询记录: {}", dto);
        RecordVO record = recordService.create(dto);
        return R.success("创建成功", record);
    }

    /**
     * 更新咨询记录
     */
    @PutMapping("/update")
    public R<RecordVO> update(@RequestBody RecordUpdateDTO dto) {
        log.info("更新咨询记录: {}", dto);
        RecordVO record = recordService.update(dto);
        return R.success("更新成功", record);
    }

    /**
     * 自动保存咨询记录（草稿）
     */
    @PostMapping("/autosave")
    public R<RecordVO> autosave(@RequestParam(required = false) String id, 
                                 @RequestBody RecordUpdateDTO dto) {
        log.info("自动保存咨询记录: id={}, dto={}", id, dto);
        RecordVO record = recordService.autosave(id, dto);
        return R.success("保存成功", record);
    }
}

