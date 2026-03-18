package sasu.platform.mhm.pojo.vo;

import lombok.Data;

/**
 * 备份列表项 VO，对应 /api/admin/data/backup/list 返回结构
 */
@Data
public class BackupItemVO {

    /**
     * 备份ID
     */
    private String id;

    /**
     * 备份文件名
     */
    private String fileName;

    /**
     * 文件大小（字节）
     */
    private Long size;

    /**
     * 状态：success / failed / running
     */
    private String status;

    /**
     * 创建时间 yyyy-MM-dd HH:mm
     */
    private String createdAt;

    /**
     * 操作人姓名
     */
    private String operator;

    /**
     * 备注
     */
    private String remark;
}


