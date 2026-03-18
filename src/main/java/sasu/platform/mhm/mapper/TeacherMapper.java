package sasu.platform.mhm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sasu.platform.mhm.pojo.dto.CounselorListQueryDTO;
import sasu.platform.mhm.pojo.dto.TeacherInboxQueryDTO;
import sasu.platform.mhm.pojo.entity.TeacherMessage;
import sasu.platform.mhm.pojo.vo.CounselorVO;
import sasu.platform.mhm.pojo.vo.TeacherMessageVO;

import java.util.List;

@Mapper
public interface TeacherMapper {

    /**
     * 插入消息（包含家长/学生/领导发给辅导员，以及辅导员回复）
     */
    int insertMessage(TeacherMessage message);

    /**
     * 更新消息（主要用于标记已读）
     */
    int updateMessage(TeacherMessage message);

    /**
     * 根据ID查询消息
     */
    TeacherMessage selectMessageById(@Param("messageId") String messageId);

    /**
     * 教师/辅导员收件箱
     */
    List<TeacherMessageVO> selectInbox(@Param("receiverId") String receiverId,
                                       @Param("query") TeacherInboxQueryDTO query);

    /**
     * 辅导员列表（领导端查看）
     */
    List<CounselorVO> selectCounselorList(@Param("query") CounselorListQueryDTO query);
}


