package sasu.platform.mhm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sasu.platform.mhm.pojo.dto.ReferralApprovalActionDTO;
import sasu.platform.mhm.pojo.dto.ReferralApprovalQueryDTO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.service.ReferralApprovalService;

/**
 * 转介审批接口
 * 对齐 /api/center/approval/referral/*
 */
@RestController
@RequestMapping("/api/center/approval/referral")
@RequiredArgsConstructor
@Slf4j
public class ReferralApprovalController {

    private final ReferralApprovalService referralApprovalService;

    /**
     * 获取转介审批列表
     * GET /api/center/approval/referral/list
     */
    @GetMapping("/list")
    public R<PageResult> list(ReferralApprovalQueryDTO query) {
        PageResult result = referralApprovalService.list(query);
        return R.success(result);
    }

    /**
     * 通过转介审批
     * POST /api/center/approval/referral/approve
     */
    @PostMapping("/approve")
    public R<Void> approve(@RequestBody ReferralApprovalActionDTO dto) {
        referralApprovalService.approve(dto);
        return R.success();
    }

    /**
     * 驳回转介审批
     * POST /api/center/approval/referral/reject
     */
    @PostMapping("/reject")
    public R<Void> reject(@RequestBody ReferralApprovalActionDTO dto) {
        referralApprovalService.reject(dto);
        return R.success();
    }
}


