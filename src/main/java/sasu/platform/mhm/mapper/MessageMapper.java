package sasu.platform.mhm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sasu.platform.mhm.pojo.dto.MessageListQueryDTO;
import sasu.platform.mhm.pojo.entity.SystemMessage;
import sasu.platform.mhm.pojo.vo.MessageVO;

import java.util.List;

/**
 * 消息Mapper
 */
@Mapper
public interface MessageMapper {

    /**
     * 查询消息列表
     */
    List<MessageVO> selectMessageList(@Param("query") MessageListQueryDTO query);

    /**
     * 查询消息详情
     */
    MessageVO selectMessageById(@Param("messageId") String messageId);

    /**
     * 查询未读消息数量
     */
    Long countUnreadMessages(@Param("userId") String userId, @Param("type") String type);

    /**
     * 插入消息
     */
    int insertMessage(SystemMessage message);

    /**
     * 批量标记已读
     */
    int markAsRead(@Param("messageIds") List<String> messageIds, @Param("userId") String userId);

    /**
     * 批量标记未读
     */
    int markAsUnread(@Param("messageIds") List<String> messageIds, @Param("userId") String userId);

    /**
     * 全部标记已读
     */
    int markAllAsRead(@Param("userId") String userId);

    /**
     * 批量删除消息
     */
    int deleteMessages(@Param("messageIds") List<String> messageIds, @Param("userId") String userId);
}

