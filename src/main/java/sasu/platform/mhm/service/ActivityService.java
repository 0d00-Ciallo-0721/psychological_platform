package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.ActivityListQueryDTO;
import sasu.platform.mhm.pojo.VO.ActivityDetailVO;

public interface ActivityService {
    PageResult list(ActivityListQueryDTO query);

    ActivityDetailVO detail(String activityId);

    void join(String activityId);

    void cancel(String activityId);

    void checkin(String activityId);

    PageResult my(Integer page, Integer pageSize);
}

