package sasu.platform.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.CaseCreateDTO;
import sasu.platform.mhm.pojo.dto.CaseListQueryDTO;
import sasu.platform.mhm.pojo.dto.CaseUpdateDTO;
import sasu.platform.mhm.pojo.vo.CaseVO;
import sasu.platform.mhm.service.CaseService;

import java.util.List;
import java.util.Map;

/**
 * 个案管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/case")
public class CaseController {

    @Autowired
    private CaseService caseService;

    /**
     * 个案列表
     */
    @GetMapping("/list")
    public R<PageResult> list(CaseListQueryDTO query) {
        log.info("查询个案列表: {}", query);
        PageResult result = caseService.list(query);
        return R.success(result);
    }

    /**
     * 个案详情
     */
    @GetMapping("/detail")
    public R<CaseVO> detail(@RequestParam String id) {
        log.info("查询个案详情: {}", id);
        CaseVO caseVO = caseService.detail(id);
        return R.success(caseVO);
    }

    /**
     * 创建个案
     */
    @PostMapping("/create")
    public R<CaseVO> create(@RequestBody CaseCreateDTO dto) {
        log.info("创建个案: {}", dto);
        CaseVO caseVO = caseService.create(dto);
        return R.success("创建成功", caseVO);
    }

    /**
     * 更新个案
     */
    @PutMapping("/update")
    public R<CaseVO> update(@RequestBody CaseUpdateDTO dto) {
        log.info("更新个案: {}", dto);
        CaseVO caseVO = caseService.update(dto);
        return R.success("更新成功", caseVO);
    }

    /**
     * 个案统计
     */
    @GetMapping("/stats")
    public R<List<Map<String, Object>>> stats(@RequestParam(required = false) String counselorId) {
        log.info("查询个案统计: counselorId={}", counselorId);
        List<Map<String, Object>> stats = caseService.stats(counselorId);
        return R.success(stats);
    }
}

