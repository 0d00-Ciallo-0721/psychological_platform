package sasu.platform.mhm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sasu.platform.mhm.pojo.vo.StatsCollegeItemVO;
import sasu.platform.mhm.pojo.vo.StatsTrendPointVO;

import java.util.List;

@Mapper
public interface AdminStatsMapper {

    long countStudents();

    long countActiveStudents(@Param("start") String start, @Param("end") String end);

    long countAppointments(@Param("start") String start, @Param("end") String end);

    long countPendingAppointments();

    long countCrisisReports(@Param("start") String start, @Param("end") String end);

    long countRegistered();

    long countVisited(@Param("start") String start, @Param("end") String end);

    long countCases(@Param("start") String start, @Param("end") String end);

    long countLongTermConsultations(@Param("start") String start, @Param("end") String end);

    List<StatsCollegeItemVO> collegeStats(@Param("start") String start, @Param("end") String end);

    List<StatsTrendPointVO> trendStats(@Param("start") String start, @Param("end") String end);
}


