package sasu.platform.mhm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.service.AdminUserService;

/**
 * 管理端-用户管理接口
 */
@RestController
@RequestMapping("/api/admin/user")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping("/list")
    public R<PageResult> list(PageQueryDTO query) {
        PageResult page = adminUserService.list(query);
        return R.success(page);
    }

    @PostMapping("/create")
    public R<Object> create() {
        // 当前版本仅支持通过其他入口创建用户，这里直接返回成功占位
        return R.success("create endpoint reserved");
    }

    @PutMapping("/update")
    public R<Object> update() {
        // 预留更新接口，具体实现可复用已有 UserController 逻辑
        return R.success("update endpoint reserved");
    }

    @DeleteMapping("/delete")
    public R<Void> delete(@RequestParam("id") String id) {
        return R.success();
    }
}


