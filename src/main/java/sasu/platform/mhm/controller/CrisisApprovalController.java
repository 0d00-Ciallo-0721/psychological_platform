package sasu.platform.mhm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sasu.platform.mhm.pojo.dto.CrisisApprovalActionDTO;
import sasu.platform.mhm.pojo.dto.CrisisApprovalQueryDTO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.service.CrisisApprovalService;

/**
 * 危机审批接口
 * 对齐 /api/center/approval/crisis/*
 */
@RestController
@RequestMapping("/api/center/approval/crisis")
@RequiredArgsConstructor
@Slf4j
public class CrisisApprovalController {

    private final CrisisApprovalService crisisApprovalService;

    /**
     * 获取危机审批列表
     * GET /api/center/approval/crisis/list
     */
    @GetMapping("/list")
    public R<PageResult> list(CrisisApprovalQueryDTO query) {
        PageResult result = crisisApprovalService.list(query);
        return R.success(result);
    }

    /**
     * 通过危机审批
     * POST /api/center/approval/crisis/approve
     */
    @PostMapping("/approve")
    public R<Void> approve(@RequestBody CrisisApprovalActionDTO dto) {
        crisisApprovalService.approve(dto);
        return R.success();
    }

    /**
     * 驳回危机审批
     * POST /api/center/approval/crisis/reject
     */
    @PostMapping("/reject")
    public R<Void> reject(@RequestBody CrisisApprovalActionDTO dto) {
        crisisApprovalService.reject(dto);
        return R.success();
    }
}


