package sasu.platform.mhm.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sasu.platform.mhm.mapper.ActivityMapper;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.ActivityListQueryDTO;
import sasu.platform.mhm.pojo.entity.GroupActivityJoin;
import sasu.platform.mhm.pojo.entity.UserDetailsImpl;
import sasu.platform.mhm.pojo.VO.ActivityDetailVO;
import sasu.platform.mhm.pojo.VO.ActivityListItemVO;
import sasu.platform.mhm.pojo.VO.MyActivityVO;
import sasu.platform.mhm.service.ActivityService;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    private String currentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            return userDetails.getUser().getUserId();
        }
        return null;
    }

    @Override
    public PageResult list(ActivityListQueryDTO query) {
        if (query == null) {
            query = new ActivityListQueryDTO();
        }
        if (query.getPage() == null || query.getPage() <= 0) {
            query.setPage(1);
        }
        if (query.getPageSize() == null || query.getPageSize() <= 0) {
            query.setPageSize(10);
        }
        String userId = currentUserId();
        PageHelper.startPage(query.getPage(), query.getPageSize());
        Page<ActivityListItemVO> page = activityMapper.listActivities(query, userId);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public ActivityDetailVO detail(String activityId) {
        if (!StringUtils.hasText(activityId)) {
            throw new IllegalArgumentException("activityId不能为空");
        }
        String userId = currentUserId();
        ActivityDetailVO detail = activityMapper.getActivityDetail(activityId, userId);
        if (detail == null) {
            throw new IllegalArgumentException("活动不存在");
        }
        return detail;
    }

    @Override
    public void join(String activityId) {
        if (!StringUtils.hasText(activityId)) {
            throw new IllegalArgumentException("activityId不能为空");
        }
        String userId = currentUserId();
        if (!StringUtils.hasText(userId)) {
            throw new IllegalArgumentException("未登录");
        }

        GroupActivityJoin existing = activityMapper.getJoinRecord(activityId, userId);
        if (existing != null && "JOINED".equals(existing.getJoinStatus())) {
            throw new IllegalArgumentException("已报名");
        }

        // 原子占位（容量控制）
        int updated = activityMapper.increaseJoinedCountIfAvailable(activityId);
        if (updated <= 0) {
            throw new IllegalArgumentException("报名失败：活动不可报名或名额已满");
        }

        Map<String, String> snapshot = activityMapper.getStudentOrgSnapshot(userId);
        String collegeName = snapshot != null ? snapshot.get("collegeName") : null;
        String className = snapshot != null ? snapshot.get("className") : null;

        GroupActivityJoin join = new GroupActivityJoin();
        join.setJoinId(IdUtil.randomUUID());
        join.setActivityId(activityId);
        join.setUserId(userId);
        join.setJoinStatus("JOINED");
        join.setJoinedAt(LocalDateTime.now());
        join.setCheckinStatus(0);
        join.setCollegeSnapshot(collegeName);
        join.setClassSnapshot(className);

        try {
            int inserted = activityMapper.insertJoinRecord(join);
            if (inserted <= 0) {
                // 理论上不会发生，兜底回滚名额
                activityMapper.decreaseJoinedCount(activityId);
                throw new IllegalArgumentException("报名失败");
            }
        } catch (Exception e) {
            // 可能触发 uk(activity_id,user_id) 唯一约束（并发重复点击）
            activityMapper.decreaseJoinedCount(activityId);
            throw e;
        }
    }

    @Override
    public void cancel(String activityId) {
        if (!StringUtils.hasText(activityId)) {
            throw new IllegalArgumentException("activityId不能为空");
        }
        String userId = currentUserId();
        if (!StringUtils.hasText(userId)) {
            throw new IllegalArgumentException("未登录");
        }
        int changed = activityMapper.cancelJoin(activityId, userId);
        if (changed <= 0) {
            throw new IllegalArgumentException("取消失败：未报名/已取消/已签到");
        }
        activityMapper.decreaseJoinedCount(activityId);
    }

    @Override
    public void checkin(String activityId) {
        if (!StringUtils.hasText(activityId)) {
            throw new IllegalArgumentException("activityId不能为空");
        }
        String userId = currentUserId();
        if (!StringUtils.hasText(userId)) {
            throw new IllegalArgumentException("未登录");
        }
        int changed = activityMapper.checkin(activityId, userId);
        if (changed <= 0) {
            throw new IllegalArgumentException("签到失败：未报名/已取消/已签到");
        }
    }

    @Override
    public PageResult my(Integer page, Integer pageSize) {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        String userId = currentUserId();
        if (!StringUtils.hasText(userId)) {
            throw new IllegalArgumentException("未登录");
        }
        PageHelper.startPage(page, pageSize);
        Page<MyActivityVO> p = activityMapper.listMyActivities(userId);
        return new PageResult(p.getTotal(), p.getResult());
    }
}

