package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 归档历史查询参数
 * 对应 GET /api/admin/data/archive/history
 */
@Data
public class ArchiveHistoryQueryDTO extends PageQueryDTO {

    /**
     * 归档任务状态：success / failed / running / all
     */
    private String statusFilter;
}


