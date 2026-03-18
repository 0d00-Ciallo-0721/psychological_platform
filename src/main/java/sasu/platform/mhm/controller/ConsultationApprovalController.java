package sasu.platform.mhm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sasu.platform.mhm.pojo.dto.ConsultationApprovalActionDTO;
import sasu.platform.mhm.pojo.dto.ConsultationApprovalQueryDTO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.service.ConsultationApprovalService;

/**
 * 咨询审批（长程申请）接口
 * 对齐 /api/center/approval/* 中长程咨询相关接口
 */
@RestController
@RequestMapping("/api/center/approval")
@RequiredArgsConstructor
@Slf4j
public class ConsultationApprovalController {

    private final ConsultationApprovalService consultationApprovalService;

    /**
     * 获取长程咨询申请审批列表
     * （具体路径根据前端 openapi，一般类似 /api/center/approval/list 或 /long-term/list）
     */
    @GetMapping("/consultation/list")
    public R<PageResult> list(ConsultationApprovalQueryDTO query) {
        PageResult result = consultationApprovalService.list(query);
        return R.success(result);
    }

    /**
     * 通过长程咨询申请
     */
    @PostMapping("/consultation/approve")
    public R<Void> approve(@RequestBody ConsultationApprovalActionDTO dto) {
        consultationApprovalService.approve(dto);
        return R.success();
    }

    /**
     * 驳回长程咨询申请
     */
    @PostMapping("/consultation/reject")
    public R<Void> reject(@RequestBody ConsultationApprovalActionDTO dto) {
        consultationApprovalService.reject(dto);
        return R.success();
    }
}


