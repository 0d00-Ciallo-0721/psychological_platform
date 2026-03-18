package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 创建备份请求体
 * 对应 POST /api/admin/data/backup
 */
@Data
public class BackupCreateDTO {

    /**
     * 备注说明（可选）
     */
    private String remark;
}


