package sasu.platform.mhm.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 字典类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictType {
    private String id;
    private String code;
    private String name;
    private Integer status;
    private String remark;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


