package sasu.platform.mhm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sasu.platform.mhm.mapper.AdminStatsMapper;
import sasu.platform.mhm.pojo.vo.StatsCollegeItemVO;
import sasu.platform.mhm.pojo.vo.StatsFunnelVO;
import sasu.platform.mhm.pojo.vo.StatsOverviewVO;
import sasu.platform.mhm.pojo.vo.StatsTrendPointVO;
import sasu.platform.mhm.service.AdminStatsService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminStatsServiceImpl implements AdminStatsService {

    private final AdminStatsMapper adminStatsMapper;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private String[] resolveRange(String range) {
        LocalDate end = LocalDate.now();
        LocalDate start;
        if ("today".equalsIgnoreCase(range)) {
            start = end;
        } else if ("week".equalsIgnoreCase(range)) {
            start = end.minusDays(6);
        } else if ("month".equalsIgnoreCase(range)) {
            start = end.withDayOfMonth(1);
        } else if ("year".equalsIgnoreCase(range)) {
            start = end.withDayOfYear(1);
        } else {
            // 默认按近30天
            start = end.minusDays(29);
        }
        return new String[]{start.format(DATE_FMT), end.format(DATE_FMT)};
    }

    @Override
    public StatsOverviewVO overview(String range) {
        String[] se = resolveRange(range);
        String start = se[0];
        String end = se[1];

        StatsOverviewVO vo = new StatsOverviewVO();
        vo.setTotalStudents(adminStatsMapper.countStudents());
        vo.setActiveStudents(adminStatsMapper.countActiveStudents(start, end));
        vo.setTotalAppointments(adminStatsMapper.countAppointments(start, end));
        vo.setPendingAppointments(adminStatsMapper.countPendingAppointments());
        vo.setCrisisReports(adminStatsMapper.countCrisisReports(start, end));
        return vo;
    }

    @Override
    public StatsFunnelVO funnel(String startDate, String endDate) {
        String start = startDate;
        String end = endDate;
        if (start == null || end == null) {
            String[] se = resolveRange("month");
            start = se[0];
            end = se[1];
        }
        StatsFunnelVO vo = new StatsFunnelVO();
        vo.setTotalRegistered(adminStatsMapper.countRegistered());
        vo.setAppointments(adminStatsMapper.countAppointments(start, end));
        vo.setVisited(adminStatsMapper.countVisited(start, end));
        vo.setCases(adminStatsMapper.countCases(start, end));
        vo.setLongTermConsultations(adminStatsMapper.countLongTermConsultations(start, end));
        return vo;
    }

    @Override
    public List<StatsCollegeItemVO> college(String startDate, String endDate) {
        String start = startDate;
        String end = endDate;
        if (start == null || end == null) {
            String[] se = resolveRange("year");
            start = se[0];
            end = se[1];
        }
        return adminStatsMapper.collegeStats(start, end);
    }

    @Override
    public List<StatsTrendPointVO> trend(String startDate, String endDate) {
        String start = startDate;
        String end = endDate;
        if (start == null || end == null) {
            String[] se = resolveRange("month");
            start = se[0];
            end = se[1];
        }
        return adminStatsMapper.trendStats(start, end);
    }
}


