package sasu.platform.mhm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sasu.platform.mhm.pojo.dto.LeaveApplyDTO;
import sasu.platform.mhm.pojo.dto.LeaveApprovalDTO;
import sasu.platform.mhm.pojo.dto.LeaveListQueryDTO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.service.LeaveService;

/**
 * 请假模块接口
 */
@RestController
@RequestMapping("/api/leave")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    /**
     * 请假列表
     */
    @GetMapping("/list")
    public R<PageResult> list(LeaveListQueryDTO query) {
        return R.success(leaveService.list(query));
    }

    /**
     * 申请请假
     */
    @PostMapping("/apply")
    public R<Void> apply(@RequestBody LeaveApplyDTO dto) {
        leaveService.apply(dto);
        return R.success();
    }

    /**
     * 取消请假
     */
    @PostMapping("/cancel")
    public R<Void> cancel(@RequestParam("leaveId") String leaveId,
                          @RequestParam(value = "reason", required = false) String reason) {
        leaveService.cancel(leaveId, reason);
        return R.success();
    }

    /**
     * 审批通过
     */
    @PostMapping("/approve")
    public R<Void> approve(@RequestBody LeaveApprovalDTO dto) {
        leaveService.approve(dto);
        return R.success();
    }

    /**
     * 审批拒绝
     */
    @PostMapping("/reject")
    public R<Void> reject(@RequestBody LeaveApprovalDTO dto) {
        leaveService.reject(dto);
        return R.success();
    }
}


