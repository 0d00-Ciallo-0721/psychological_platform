package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.vo.StatsCollegeItemVO;
import sasu.platform.mhm.pojo.vo.StatsFunnelVO;
import sasu.platform.mhm.pojo.vo.StatsOverviewVO;
import sasu.platform.mhm.pojo.vo.StatsTrendPointVO;

import java.util.List;

public interface AdminStatsService {

    StatsOverviewVO overview(String range);

    StatsFunnelVO funnel(String startDate, String endDate);

    List<StatsCollegeItemVO> college(String startDate, String endDate);

    List<StatsTrendPointVO> trend(String startDate, String endDate);
}


