package sasu.platform.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.ParentBindChildDTO;
import sasu.platform.mhm.pojo.vo.ChildVO;
import sasu.platform.mhm.pojo.vo.CounselorDetailVO;
import sasu.platform.mhm.service.ParentService;

import java.util.List;

/**
 * 家长端控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/parent")
public class ParentController {

    @Autowired
    private ParentService parentService;

    /**
     * 子女列表
     */
    @GetMapping("/children/list")
    public R<List<ChildVO>> getChildrenList() {
        log.info("查询子女列表");
        List<ChildVO> children = parentService.getChildrenList(null);
        return R.success(children);
    }

    /**
     * 子女详情
     */
    @GetMapping("/children/detail")
    public R<ChildVO> getChildDetail(@RequestParam String studentId) {
        log.info("查询子女详情: {}", studentId);
        ChildVO child = parentService.getChildDetail(studentId);
        return R.success(child);
    }

    /**
     * 绑定子女
     */
    @PostMapping("/children/bind")
    public R<String> bindChild(@RequestBody ParentBindChildDTO dto) {
        log.info("绑定子女: {}", dto);
        parentService.bindChild(dto, null);
        return R.success("绑定成功");
    }

    /**
     * 解绑子女
     */
    @PostMapping("/children/unbind")
    public R<String> unbindChild(@RequestParam String childId) {
        log.info("解绑子女: {}", childId);
        parentService.unbindChild(childId, null);
        return R.success("解绑成功");
    }

    /**
     * 获取子女的辅导员信息
     */
    @GetMapping("/counselor")
    public R<List<CounselorDetailVO>> getCounselor(@RequestParam String studentId) {
        log.info("获取子女的辅导员信息: {}", studentId);
        List<CounselorDetailVO> counselors = parentService.getCounselors(studentId);
        return R.success(counselors);
    }

    /**
     * 查询子女测评记录
     */
    @GetMapping("/children/assessments")
    public R<PageResult> getChildAssessments(@RequestParam String studentId,
                                             @RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询子女测评记录: studentId={}, page={}, pageSize={}", studentId, page, pageSize);
        PageResult result = parentService.getChildAssessments(studentId, page, pageSize);
        return R.success(result);
    }

    /**
     * 查询子女预约记录
     */
    @GetMapping("/children/appointments")
    public R<PageResult> getChildAppointments(@RequestParam String studentId,
                                              @RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询子女预约记录: studentId={}, page={}, pageSize={}", studentId, page, pageSize);
        PageResult result = parentService.getChildAppointments(studentId, page, pageSize);
        return R.success(result);
    }

    /**
     * 查询子女活动记录
     */
    @GetMapping("/children/activities")
    public R<PageResult> getChildActivities(@RequestParam String studentId,
                                           @RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询子女活动记录: studentId={}, page={}, pageSize={}", studentId, page, pageSize);
        PageResult result = parentService.getChildActivities(studentId, page, pageSize);
        return R.success(result);
    }
}

