package sasu.platform.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.ActivityListQueryDTO;
import sasu.platform.mhm.pojo.VO.ActivityDetailVO;
import sasu.platform.mhm.service.ActivityService;

@Slf4j
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 活动列表
     */
    @GetMapping("/list")
    public R<PageResult> list(ActivityListQueryDTO query) {
        log.info("活动列表查询: {}", query);
        PageResult result = activityService.list(query);
        return R.success(result);
    }

    /**
     * 活动详情
     */
    @GetMapping("/detail")
    public R<ActivityDetailVO> detail(@RequestParam(value = "activityId", required = false) String activityId,
                                      @RequestParam(value = "id", required = false) String id) {
        // 兼容前端使用 id 参数的情况
        String finalId = activityId != null ? activityId : id;
        log.info("活动详情: {}", finalId);
        ActivityDetailVO detail = activityService.detail(finalId);
        return R.success(detail);
    }

    /**
     * 报名活动
     */
    @PostMapping("/join")
    public R<String> join(@RequestParam String activityId) {
        log.info("报名活动: {}", activityId);
        activityService.join(activityId);
        return R.success("报名成功");
    }

    /**
     * 报名活动（前端别名：/api/activity/signup）
     */
    @PostMapping("/signup")
    public R<String> signup(@RequestParam String activityId) {
        log.info("报名活动(signup): {}", activityId);
        activityService.join(activityId);
        return R.success("报名成功");
    }

    /**
     * 取消报名
     */
    @PostMapping("/cancel")
    public R<String> cancel(@RequestParam String activityId) {
        log.info("取消报名: {}", activityId);
        activityService.cancel(activityId);
        return R.success("取消成功");
    }

    /**
     * 签到
     */
    @PostMapping("/checkin")
    public R<String> checkin(@RequestParam String activityId) {
        log.info("活动签到: {}", activityId);
        activityService.checkin(activityId);
        return R.success("签到成功");
    }

    /**
     * 我的活动
     */
    @GetMapping("/my")
    public R<PageResult> my(@RequestParam Integer page,
                            @RequestParam Integer pageSize) {
        log.info("我的活动: page={}, pageSize={}", page, pageSize);
        PageResult result = activityService.my(page, pageSize);
        return R.success(result);
    }

    /**
     * 我的活动（前端别名：/api/activity/my-list）
     */
    @GetMapping("/my-list")
    public R<PageResult> myList(@RequestParam Integer page,
                                @RequestParam Integer pageSize) {
        log.info("我的活动(my-list): page={}, pageSize={}", page, pageSize);
        PageResult result = activityService.my(page, pageSize);
        return R.success(result);
    }
}

