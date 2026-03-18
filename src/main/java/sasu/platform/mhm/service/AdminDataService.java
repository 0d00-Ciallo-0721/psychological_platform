package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.*;
import sasu.platform.mhm.pojo.vo.ArchiveHistoryItemVO;
import sasu.platform.mhm.pojo.vo.BackupItemVO;
import sasu.platform.mhm.pojo.vo.DataArchiveConfigVO;

public interface AdminDataService {

    PageResult listBackup(BackupListQueryDTO query);

    BackupItemVO createBackup(BackupCreateDTO dto);

    void restoreBackup(BackupRestoreDTO dto);

    DataArchiveConfigVO getArchiveConfig();

    void saveArchiveConfig(ArchiveConfigDTO dto);

    PageResult listArchiveHistory(ArchiveHistoryQueryDTO query);
}


