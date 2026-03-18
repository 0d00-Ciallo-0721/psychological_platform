package sasu.platform.mhm.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sasu.platform.mhm.pojo.dto.ArchiveHistoryQueryDTO;
import sasu.platform.mhm.pojo.dto.BackupListQueryDTO;
import sasu.platform.mhm.pojo.entity.ArchiveConfig;
import sasu.platform.mhm.pojo.entity.ArchiveHistory;
import sasu.platform.mhm.pojo.entity.BackupRecord;

@Mapper
public interface AdminDataMapper {

    Page<BackupRecord> pageBackup(BackupListQueryDTO query);

    int insertBackup(BackupRecord record);

    BackupRecord getBackupById(@Param("id") String id);

    ArchiveConfig getArchiveConfig();

    int upsertArchiveConfig(ArchiveConfig config);

    Page<ArchiveHistory> pageArchiveHistory(ArchiveHistoryQueryDTO query);

    int insertArchiveHistory(ArchiveHistory history);
}


