package sasu.platform.mhm.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 字典项
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictItem {
    private String id;
    private String typeCode;
    private String label;
    private String value;
    private Integer orderNo;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


