package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;

public interface AdminUserService {

    PageResult list(PageQueryDTO query);

}


