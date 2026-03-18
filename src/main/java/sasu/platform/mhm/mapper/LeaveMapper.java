package sasu.platform.mhm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sasu.platform.mhm.pojo.dto.LeaveListQueryDTO;
import sasu.platform.mhm.pojo.entity.LeaveRequest;
import sasu.platform.mhm.pojo.vo.LeaveVO;

import java.util.List;

@Mapper
public interface LeaveMapper {

    /**
     * 新增请假申请
     */
    int insert(LeaveRequest request);

    /**
     * 更新请假申请
     */
    int update(LeaveRequest request);

    /**
     * 按ID查询
     */
    LeaveRequest selectById(@Param("leaveId") String leaveId);

    /**
     * 请假列表
     */
    List<LeaveVO> selectList(@Param("query") LeaveListQueryDTO query);
}


