package sasu.platform.mhm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.service.DictService;

/**
 * 管理端-字典管理接口（类型 & 字典项）
 */
@RestController
@RequestMapping("/api/admin/data")
@RequiredArgsConstructor
public class AdminDictController {

    private final DictService dictService;

    @GetMapping("/dict/types")
    public R<PageResult> listTypes(PageQueryDTO query) {
        PageResult page = dictService.listTypes(query);
        return R.success(page);
    }

    @PostMapping("/dict/types")
    public R<Object> saveType(@RequestBody Object body) {
        // 简化：由服务内部解析/转换
        Object vo = dictService.saveType(body);
        return R.success(vo);
    }

    @GetMapping("/dict/items")
    public R<PageResult> listItems(@RequestParam("typeCode") String typeCode,
                                   @RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        PageResult result = dictService.listItems(typeCode, page, pageSize);
        return R.success(result);
    }

    @PostMapping("/dict/items")
    public R<Object> saveItem(@RequestBody Object body) {
        Object vo = dictService.saveItem(body);
        return R.success(vo);
    }

    @DeleteMapping("/dict/items/{id}")
    public R<Void> deleteItem(@PathVariable("id") String id) {
        dictService.deleteItem(id);
        return R.success();
    }
}



