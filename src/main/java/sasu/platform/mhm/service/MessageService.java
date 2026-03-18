package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.MessageListQueryDTO;
import sasu.platform.mhm.pojo.dto.MessageMarkDTO;
import sasu.platform.mhm.pojo.dto.MessageSendDTO;
import sasu.platform.mhm.pojo.vo.MessageVO;

import java.util.List;

/**
 * 消息服务接口
 */
public interface MessageService {

    /**
     * 查询消息列表
     */
    PageResult list(MessageListQueryDTO query);

    /**
     * 查询消息详情
     */
    MessageVO detail(String messageId);

    /**
     * 查询未读消息数量
     */
    Long getUnreadCount(String userId, String type);

    /**
     * 发送消息
     */
    void sendMessage(MessageSendDTO dto);

    /**
     * 标记已读
     */
    void markAsRead(MessageMarkDTO dto, String userId);

    /**
     * 标记未读
     */
    void markAsUnread(MessageMarkDTO dto, String userId);

    /**
     * 全部标记已读
     */
    void markAllAsRead(String userId);

    /**
     * 删除消息
     */
    void deleteMessages(List<String> messageIds, String userId);
}

