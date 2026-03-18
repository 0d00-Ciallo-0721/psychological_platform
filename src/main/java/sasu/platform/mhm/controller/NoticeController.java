package sasu.platform.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.NoticeCreateDTO;
import sasu.platform.mhm.pojo.dto.NoticeListQueryDTO;
import sasu.platform.mhm.pojo.dto.NoticeUpdateDTO;
import sasu.platform.mhm.pojo.vo.CmsContentVO;
import sasu.platform.mhm.pojo.vo.CmsContentVO;
import sasu.platform.mhm.service.NoticeService;

/**
 * 通知公告控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 通知列表
     */
    @GetMapping("/list")
    public R<PageResult> list(NoticeListQueryDTO query) {
        log.info("查询通知列表: {}", query);
        PageResult result = noticeService.list(query);
        return R.success(result);
    }

    /**
     * 通知详情
     */
    @GetMapping("/detail")
    public R<CmsContentVO> detail(@RequestParam Long id) {
        log.info("查询通知详情: {}", id);
        CmsContentVO notice = noticeService.detail(id);
        return R.success(notice);
    }

    /**
     * 创建通知
     */
    @PostMapping("/create")
    public R<CmsContentVO> create(@RequestBody NoticeCreateDTO dto) {
        log.info("创建通知: {}", dto);
        CmsContentVO notice = noticeService.create(dto);
        return R.success("创建成功", notice);
    }

    /**
     * 更新通知
     */
    @PutMapping("/update")
    public R<CmsContentVO> update(@RequestBody NoticeUpdateDTO dto) {
        log.info("更新通知: {}", dto);
        CmsContentVO notice = noticeService.update(dto);
        return R.success("更新成功", notice);
    }

    /**
     * 删除通知
     */
    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam Long id) {
        log.info("删除通知: {}", id);
        noticeService.delete(id);
        return R.success("删除成功");
    }
}

