package sasu.platform.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sasu.platform.mhm.pojo.vo.CounselorDetailVO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.service.ConsulateService;

/**
 * 咨询师相关接口
 *
 * 对应前端：/api/consultant/*
 */
@Slf4j
@RestController
@RequestMapping("/api/consultant")
public class ConsultantController {

    @Autowired
    private ConsulateService consulateService;

    /**
     * 咨询师列表
     *
     * 对应前端：GET /api/consultant/list
     */
    @GetMapping("/list")
    public R<PageResult> list(PageQueryDTO queryDTO) {
        log.info("查询咨询师列表: {}", queryDTO);
        PageResult pageResult = consulateService.counselorList(queryDTO);
        return R.success(pageResult);
    }

    /**
     * 咨询师详情
     *
     * 对应前端：GET /api/consultant/detail
     */
    @GetMapping("/detail")
    public R<CounselorDetailVO> detail(@RequestParam String counselorId) {
        log.info("查询咨询师详情: {}", counselorId);
        CounselorDetailVO detailVO = consulateService.getCounselorDetail(counselorId);
        return R.success(detailVO);
    }
}


