package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 恢复备份请求体
 * 对应 POST /api/admin/data/restore
 */
@Data
public class BackupRestoreDTO {

    /**
     * 备份ID
     */
    private String backupId;

    /**
     * 是否确认（前端二次确认后传 true）
     */
    private Boolean confirm;
}


