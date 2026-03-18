package sasu.platform.mhm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sasu.platform.mhm.mapper.UserMapper;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.entity.User;
import sasu.platform.mhm.service.AdminUserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserMapper userMapper;

    @Override
    public PageResult list(PageQueryDTO query) {
        if (query.getPage() == null || query.getPageSize() == null) {
            query.setPage(1);
            query.setPageSize(10);
        }
        PageHelper.startPage(query.getPage(), query.getPageSize());
        Page<User> page = (Page<User>) userMapper.selectAdminUserList(query);
        List<User> records = page.getResult();
        return new PageResult(page.getTotal(), records);
    }
}


