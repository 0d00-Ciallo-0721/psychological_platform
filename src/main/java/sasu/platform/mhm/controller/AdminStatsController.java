package sasu.platform.mhm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.vo.StatsCollegeItemVO;
import sasu.platform.mhm.pojo.vo.StatsFunnelVO;
import sasu.platform.mhm.pojo.vo.StatsOverviewVO;
import sasu.platform.mhm.pojo.vo.StatsTrendPointVO;
import sasu.platform.mhm.service.AdminStatsService;

import java.util.List;

/**
 * 管理端-统计数据接口
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminStatsController {

    private final AdminStatsService adminStatsService;

    @GetMapping("/stats/overview")
    public R<StatsOverviewVO> overview(@RequestParam(value = "range", required = false) String range) {
        StatsOverviewVO vo = adminStatsService.overview(range);
        return R.success(vo);
    }

    @GetMapping("/stats/funnel")
    public R<StatsFunnelVO> funnel(@RequestParam(value = "startDate", required = false) String startDate,
                                   @RequestParam(value = "endDate", required = false) String endDate) {
        StatsFunnelVO vo = adminStatsService.funnel(startDate, endDate);
        return R.success(vo);
    }

    @GetMapping("/stats/college")
    public R<List<StatsCollegeItemVO>> college(@RequestParam(value = "startDate", required = false) String startDate,
                                               @RequestParam(value = "endDate", required = false) String endDate) {
        List<StatsCollegeItemVO> list = adminStatsService.college(startDate, endDate);
        return R.success(list);
    }

    @GetMapping("/stats/trend")
    public R<List<StatsTrendPointVO>> trend(@RequestParam(value = "startDate", required = false) String startDate,
                                            @RequestParam(value = "endDate", required = false) String endDate) {
        List<StatsTrendPointVO> list = adminStatsService.trend(startDate, endDate);
        return R.success(list);
    }
}


