package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 备份列表查询参数
 * 对应 GET /api/admin/data/backup/list
 */
@Data
public class BackupListQueryDTO extends PageQueryDTO {

    /**
     * 关键字（备注、执行人等模糊搜索）
     */
    private String keyword;

    /**
     * 备份状态过滤：success / failed / running / all
     */
    private String statusFilter;
}


