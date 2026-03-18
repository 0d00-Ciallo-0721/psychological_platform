package sasu.platform.mhm.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * 标记消息DTO
 */
@Data
public class MessageMarkDTO {
    /**
     * 消息ID列表
     */
    private List<String> messageIds;

    /**
     * 是否全部标记
     */
    private Boolean all;
}

