package sasu.platform.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.vo.CmsContentVO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.CmsCreateDTO;
import sasu.platform.mhm.pojo.dto.CmsUpdateDTO;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.entity.CmsContent;
import sasu.platform.mhm.service.CmsService;

import java.util.List;

/**
 * 互助美文等控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/cms")
public class CmsController {


    @Autowired
    private CmsService cmsService;

    /**
     * 分页分类、多条件获取内容列表
     */
    @PostMapping("/cms-s")
    public R<PageResult> getCms_s(@RequestBody PageQueryDTO pageQueryDTO) {

        log.info("查询内容列表接口:{}",pageQueryDTO);
        int status = 1;
        PageResult pageResult =cmsService.getCmss(status,pageQueryDTO);
        return R.success(pageResult);
    };

    /**
     * 获取内容详情
     */
    @GetMapping("/detail")
    public R getCmsDetail(@RequestParam Long id) {
         log.info("获取内容详情接口:{}",id);
         CmsContentVO cmsContent = cmsService.getCmsDetail(id);
         return R.success(cmsContent);
    }

    /**
     * 活动风采列表（type=1）
     */
    @GetMapping("/activities")
    public R<PageResult> getActivities(PageQueryDTO query) {
        log.info("查询活动风采列表: {}", query);
        if (query == null) {
            query = new PageQueryDTO();
        }
        query.setType(1);
        PageResult result = cmsService.getCmss(1, query);
        return R.success(result);
    }

    /**
     * 通知公告列表（type=2）
     */
    @GetMapping("/notices")
    public R<PageResult> getNotices(PageQueryDTO query) {
        log.info("查询通知公告列表: {}", query);
        if (query == null) {
            query = new PageQueryDTO();
        }
        query.setType(2);
        PageResult result = cmsService.getCmss(1, query);
        return R.success(result);
    }

    /**
     * 朋辈互助列表（type=3）
     */
    @GetMapping("/peer-support")
    public R<PageResult> getPeerSupport(PageQueryDTO query) {
        log.info("查询朋辈互助列表: {}", query);
        if (query == null) {
            query = new PageQueryDTO();
        }
        query.setType(3);
        PageResult result = cmsService.getCmss(1, query);
        return R.success(result);
    }

    /**
     * 心理百科列表（type=4）
     */
    @GetMapping("/wiki")
    public R<PageResult> getWiki(PageQueryDTO query) {
        log.info("查询心理百科列表: {}", query);
        if (query == null) {
            query = new PageQueryDTO();
        }
        query.setType(4);
        PageResult result = cmsService.getCmss(1, query);
        return R.success(result);
    }

    /**
     * 心理美文列表（type=5）
     */
    @GetMapping("/articles")
    public R<PageResult> getArticles(PageQueryDTO query) {
        log.info("查询心理美文列表: {}", query);
        if (query == null) {
            query = new PageQueryDTO();
        }
        query.setType(5);
        PageResult result = cmsService.getCmss(1, query);
        return R.success(result);
    }

    /**
     * 创建内容（管理端）
     */
    @PostMapping("/create")
    public R<CmsContentVO> create(@RequestBody CmsCreateDTO dto) {
        log.info("创建内容: {}", dto);
        CmsContentVO content = cmsService.create(dto);
        return R.success("创建成功", content);
    }

    /**
     * 更新内容（管理端）
     */
    @PutMapping("/update")
    public R<CmsContentVO> update(@RequestBody CmsUpdateDTO dto) {
        log.info("更新内容: {}", dto);
        CmsContentVO content = cmsService.update(dto);
        return R.success("更新成功", content);
    }

    /**
     * 删除内容（管理端）
     */
    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam Long id) {
        log.info("删除内容: {}", id);
        cmsService.delete(id);
        return R.success("删除成功");
    }

    /**
     * 发布内容（管理端）
     */
    @PostMapping("/publish")
    public R<String> publish(@RequestParam Long id) {
        log.info("发布内容: {}", id);
        cmsService.publish(id);
        return R.success("发布成功");
    }
}
