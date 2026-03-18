package sasu.platform.mhm.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.VO.CrisisReportVO;
import sasu.platform.mhm.pojo.VO.CrisistLevelVO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.CrisisListQueryDTO;
import sasu.platform.mhm.pojo.dto.CrisisReportCreateDTO;
import sasu.platform.mhm.pojo.dto.CrisistLevelDTO;
import sasu.platform.mhm.service.CrisisCaseService;

@Slf4j
@RestController
@RequestMapping("/api/crisis")
public class CrisisCaseController {
    @Autowired
    private CrisisCaseService crisisCaseService;

    /**
     * 危机上报
     * @param crisisReportCreateDTO
     * @return
     */
    @PostMapping("/report")
    public R report(@RequestBody CrisisReportCreateDTO crisisReportCreateDTO){
        log.info("危机个案上报{}",crisisReportCreateDTO);
        crisisCaseService.report(crisisReportCreateDTO);
        return R.success("设置成功");

    }

    @GetMapping("/list")
    public R getCrisisList(@RequestParam Integer page,
                           @RequestParam Integer pageSize,
                           CrisisListQueryDTO crisisListQueryDTO) {
        log.info("开始分页查询，页数：{}，大小：{}，条件：{}",page,pageSize,crisisListQueryDTO);
        PageResult result = crisisCaseService.getCrisisList(page,pageSize,crisisListQueryDTO);
        return R.success(result);
    }

    @PostMapping("/update-level")
    public R setLevel(@RequestBody CrisistLevelDTO crisistLevelDTO){
        log.info("更新上报等级，id:{},crisisLevel:{}",crisistLevelDTO.getReportId(),crisistLevelDTO.getCrisisLevel());
        if((crisistLevelDTO.getCrisisLevel()==null||crisistLevelDTO.getCrisisLevel().equals(""))&& (crisistLevelDTO.getReportId()==null || crisistLevelDTO.getReportId().equals("")) ){
            return R.error("参数不能为空");
        }
        CrisistLevelVO result = crisisCaseService.setLevel(crisistLevelDTO);
        return R.success(result);
    }

    /**
     * 危机详情占位接口
     *
     * 前端期望：GET /api/crisis/detail?id=...
     * 当前暂未实现完整 VO，这里先返回空数据结构，避免前端 404。
     */
    @GetMapping("/detail")
    public R<CrisisReportVO> detail(@RequestParam String id) {
        log.info("查询危机详情，占位实现, id={}", id);
        // 后续可以在 CrisisCaseService 中增加按 id 查询的能力
        return R.success(null);
    }
}
