package sasu.platform.mhm.mapper;

import org.apache.ibatis.annotations.Mapper;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectById(String userid);

    User selectByUsername(String username);

    void updateById(User userInfo);

    /**
     * 管理端用户分页列表查询
     */
    List<User> selectAdminUserList(PageQueryDTO query);
}
