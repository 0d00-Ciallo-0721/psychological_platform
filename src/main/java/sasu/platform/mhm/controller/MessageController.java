package sasu.platform.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.MessageListQueryDTO;
import sasu.platform.mhm.pojo.dto.MessageMarkDTO;
import sasu.platform.mhm.pojo.dto.MessageSendDTO;
import sasu.platform.mhm.pojo.vo.MessageVO;
import sasu.platform.mhm.service.MessageService;

import java.util.List;
import java.util.Map;

/**
 * 消息中心控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 消息列表
     */
    @GetMapping("/list")
    public R<PageResult> list(MessageListQueryDTO query) {
        log.info("查询消息列表: {}", query);
        PageResult result = messageService.list(query);
        return R.success(result);
    }

    /**
     * 获取未读消息数量
     */
    @GetMapping("/unread-count")
    public R<Long> getUnreadCount(@RequestParam(required = false) String type) {
        log.info("查询未读消息数量, type={}", type);
        Long count = messageService.getUnreadCount(null, type);
        return R.success(count);
    }

    /**
     * 消息详情
     */
    @GetMapping("/detail")
    public R<MessageVO> detail(@RequestParam String id) {
        log.info("查询消息详情: {}", id);
        MessageVO message = messageService.detail(id);
        return R.success(message);
    }

    /**
     * 发送消息
     */
    @PostMapping("/send")
    public R<String> send(@RequestBody MessageSendDTO dto) {
        log.info("发送消息: {}", dto);
        messageService.sendMessage(dto);
        return R.success("发送成功");
    }

    /**
     * 标记已读
     */
    @PostMapping("/mark-read")
    public R<String> markAsRead(@RequestBody MessageMarkDTO dto) {
        log.info("标记已读: {}", dto);
        messageService.markAsRead(dto, null);
        return R.success("标记成功");
    }

    /**
     * 标记未读
     */
    @PostMapping("/mark-unread")
    public R<String> markAsUnread(@RequestBody MessageMarkDTO dto) {
        log.info("标记未读: {}", dto);
        messageService.markAsUnread(dto, null);
        return R.success("标记成功");
    }

    /**
     * 全部标记已读
     */
    @PostMapping("/mark-all-read")
    public R<String> markAllAsRead() {
        log.info("全部标记已读");
        messageService.markAllAsRead(null);
        return R.success("全部标记已读成功");
    }

    /**
     * 删除消息
     */
    @DeleteMapping("/delete")
    public R<String> delete(@RequestBody Map<String, List<String>> body) {
        List<String> messageIds = body.get("messageIds");
        log.info("删除消息: {}", messageIds);
        messageService.deleteMessages(messageIds, null);
        return R.success("删除成功");
    }
}

