package sasu.platform.mhm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sasu.platform.mhm.mapper.DictMapper;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.entity.DictItem;
import sasu.platform.mhm.pojo.entity.DictType;
import sasu.platform.mhm.service.DictService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DictServiceImpl implements DictService {

    private final DictMapper dictMapper;

    @Override
    public PageResult listTypes(PageQueryDTO query) {
        if (query.getPage() == null || query.getPageSize() == null) {
            query.setPage(1);
            query.setPageSize(10);
        }
        PageHelper.startPage(query.getPage(), query.getPageSize());
        Page<DictType> page = (Page<DictType>) dictMapper.selectTypePage(query);
        List<DictType> records = page.getResult();
        return new PageResult(page.getTotal(), records);
    }

    @Override
    public Object saveType(Object body) {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) body;
        DictType type = new DictType();
        type.setId(map.getOrDefault("id", UUID.randomUUID().toString()).toString());
        type.setCode((String) map.get("code"));
        type.setName((String) map.get("name"));
        type.setStatus(map.get("status") == null ? 1 : (Integer) map.get("status"));
        type.setRemark((String) map.get("remark"));
        if (map.get("id") == null) {
            dictMapper.insertType(type);
        } else {
            dictMapper.updateType(type);
        }
        return type;
    }

    @Override
    public PageResult listItems(String typeCode, Integer page, Integer pageSize) {
        if (page == null || pageSize == null) {
            page = 1;
            pageSize = 10;
        }
        int offset = (page - 1) * pageSize;
        List<DictItem> list = dictMapper.selectItemPage(typeCode, offset, pageSize);
        // 简单起见，不做 count 查询，可根据需要扩展
        long total = list.size();
        return new PageResult(total, list);
    }

    @Override
    public Object saveItem(Object body) {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) body;
        DictItem item = new DictItem();
        item.setId(map.getOrDefault("id", UUID.randomUUID().toString()).toString());
        item.setTypeCode((String) map.get("typeCode"));
        item.setLabel((String) map.get("label"));
        item.setValue((String) map.get("value"));
        item.setOrderNo(map.get("orderNo") == null ? 0 : (Integer) map.get("orderNo"));
        item.setStatus(map.get("status") == null ? 1 : (Integer) map.get("status"));
        if (map.get("id") == null) {
            dictMapper.insertItem(item);
        } else {
            dictMapper.updateItem(item);
        }
        return item;
    }

    @Override
    public void deleteItem(String id) {
        dictMapper.deleteItem(id);
    }
}


