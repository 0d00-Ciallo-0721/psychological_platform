package sasu.platform.mhm.controller;

import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;

import java.util.Collections;

/**
 * 报表模块接口
 */
@RestController
@RequestMapping("/api")
public class ReportController {

    @GetMapping("/admin/report/monthly")
    public R<Object> monthly() {
        return R.success(Collections.emptyMap());
    }

    @GetMapping("/admin/report/download/{id}")
    public Object download(@PathVariable("id") String id) {
        return null;
    }

    @GetMapping("/report/list")
    public R<PageResult> list() {
        return R.success(new PageResult(0, java.util.List.of()));
    }

    @GetMapping("/report/export")
    public Object export() {
        return null;
    }

    @PostMapping("/report/generate")
    public R<Object> generate() {
        return R.success("generate endpoint reserved");
    }

    @GetMapping("/report/history")
    public R<PageResult> history() {
        return R.success(new PageResult(0, java.util.List.of()));
    }

    @PostMapping("/report/preview")
    public R<Object> preview() {
        return R.success("preview endpoint reserved");
    }
}


