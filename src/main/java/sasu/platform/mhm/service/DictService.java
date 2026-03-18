package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;

public interface DictService {

    PageResult listTypes(PageQueryDTO query);

    Object saveType(Object body);

    PageResult listItems(String typeCode, Integer page, Integer pageSize);

    Object saveItem(Object body);

    void deleteItem(String id);
}


