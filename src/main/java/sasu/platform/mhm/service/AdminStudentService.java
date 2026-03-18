package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.AdminStudentQueryDTO;
import sasu.platform.mhm.pojo.vo.AdminStudentVO;

public interface AdminStudentService {

    PageResult page(AdminStudentQueryDTO query);

    AdminStudentVO detail(String studentId);
}


