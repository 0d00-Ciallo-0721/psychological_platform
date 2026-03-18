package sasu.platform.mhm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sasu.platform.mhm.pojo.entity.AssessmentPlan;
import sasu.platform.mhm.pojo.entity.AssessmentRecord;

import java.util.List;
import java.util.Map;

@Mapper
public interface AssessmentMapper {

    boolean insertPlan(AssessmentPlan plan);

    boolean updatePlan(AssessmentPlan plan);

    AssessmentPlan getPlanById(@Param("planId") String planId);

    /**
     * 按条件查询方案列表
     */
    List<AssessmentPlan> listPlans(Map<String, Object> params);

    boolean insertRecord(AssessmentRecord record);

    boolean updateRecord(AssessmentRecord record);

    AssessmentRecord getRecordById(@Param("recordId") String recordId);

    /**
     * 当前用户在某方案下的已提交测评次数
     */
    Integer countSubmittedByUserAndPlan(@Param("userId") String userId,
                                        @Param("planId") String planId);

    /**
     * 我的测评记录列表
     */
    List<AssessmentRecord> listRecordsByUser(@Param("userId") String userId);
}

