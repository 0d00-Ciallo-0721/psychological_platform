package sasu.platform.mhm.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sasu.platform.mhm.pojo.dto.ActivityListQueryDTO;
import sasu.platform.mhm.pojo.entity.GroupActivityJoin;
import sasu.platform.mhm.pojo.VO.ActivityDetailVO;
import sasu.platform.mhm.pojo.VO.ActivityListItemVO;
import sasu.platform.mhm.pojo.VO.MyActivityVO;

import java.util.Map;

@Mapper
public interface ActivityMapper {

    Page<ActivityListItemVO> listActivities(@Param("q") ActivityListQueryDTO query,
                                            @Param("userId") String userId);

    ActivityDetailVO getActivityDetail(@Param("activityId") String activityId,
                                       @Param("userId") String userId);

    GroupActivityJoin getJoinRecord(@Param("activityId") String activityId,
                                    @Param("userId") String userId);

    int insertJoinRecord(GroupActivityJoin join);

    int cancelJoin(@Param("activityId") String activityId,
                   @Param("userId") String userId);

    int checkin(@Param("activityId") String activityId,
                @Param("userId") String userId);

    int increaseJoinedCountIfAvailable(@Param("activityId") String activityId);

    int decreaseJoinedCount(@Param("activityId") String activityId);

    Page<MyActivityVO> listMyActivities(@Param("userId") String userId);

    /**
     * 查询报名时需要写入的学院/班级快照（名称）
     * 返回key：collegeName, className
     */
    Map<String, String> getStudentOrgSnapshot(@Param("userId") String userId);
}

