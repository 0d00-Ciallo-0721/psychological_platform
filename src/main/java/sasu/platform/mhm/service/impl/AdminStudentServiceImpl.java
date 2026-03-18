package sasu.platform.mhm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sasu.platform.mhm.mapper.AdminStudentMapper;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.AdminStudentQueryDTO;
import sasu.platform.mhm.pojo.vo.AdminStudentVO;
import sasu.platform.mhm.service.AdminStudentService;

@Service
@RequiredArgsConstructor
public class AdminStudentServiceImpl implements AdminStudentService {

    private final AdminStudentMapper adminStudentMapper;

    @Override
    public PageResult page(AdminStudentQueryDTO query) {
        if (query.getPage() == null || query.getPageSize() == null) {
            query.setPage(1);
            query.setPageSize(10);
        }
        PageHelper.startPage(query.getPage(), query.getPageSize());
        Page<AdminStudentVO> page = adminStudentMapper.pageQuery(query);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public AdminStudentVO detail(String studentId) {
        return adminStudentMapper.getByStudentId(studentId);
    }
}


