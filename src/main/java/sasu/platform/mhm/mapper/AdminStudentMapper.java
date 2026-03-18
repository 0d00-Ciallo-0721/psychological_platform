package sasu.platform.mhm.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sasu.platform.mhm.pojo.dto.AdminStudentQueryDTO;
import sasu.platform.mhm.pojo.vo.AdminStudentVO;

@Mapper
public interface AdminStudentMapper {

    Page<AdminStudentVO> pageQuery(AdminStudentQueryDTO query);

    AdminStudentVO getByStudentId(@Param("studentId") String studentId);
}


