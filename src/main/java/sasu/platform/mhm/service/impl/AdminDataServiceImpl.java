package sasu.platform.mhm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import sasu.platform.mhm.mapper.AdminDataMapper;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.*;
import sasu.platform.mhm.pojo.entity.ArchiveConfig;
import sasu.platform.mhm.pojo.entity.ArchiveHistory;
import sasu.platform.mhm.pojo.entity.BackupRecord;
import sasu.platform.mhm.pojo.vo.ArchiveHistoryItemVO;
import sasu.platform.mhm.pojo.vo.BackupItemVO;
import sasu.platform.mhm.pojo.vo.DataArchiveConfigVO;
import sasu.platform.mhm.service.AdminDataService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminDataServiceImpl implements AdminDataService {

    private final AdminDataMapper adminDataMapper;

    private static final DateTimeFormatter DT_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public PageResult listBackup(BackupListQueryDTO query) {
        if (query.getPage() == null || query.getPageSize() == null) {
            query.setPage(1);
            query.setPageSize(10);
        }
        PageHelper.startPage(query.getPage(), query.getPageSize());
        Page<BackupRecord> page = adminDataMapper.pageBackup(query);
        List<BackupItemVO> list = page.getResult().stream().map(this::toBackupVO).collect(Collectors.toList());
        return new PageResult(page.getTotal(), list);
    }

    @Override
    public BackupItemVO createBackup(BackupCreateDTO dto) {
        BackupRecord record = new BackupRecord();
        record.setId(UUID.randomUUID().toString());
        record.setFileName("backup-" + System.currentTimeMillis() + ".sql");
        record.setSize(0L);
        record.setStatus("success");
        record.setRemark(dto.getRemark());
        record.setOperator("system");
        adminDataMapper.insertBackup(record);
        return toBackupVO(record);
    }

    @Override
    public void restoreBackup(BackupRestoreDTO dto) {
        if (dto.getConfirm() == null || !dto.getConfirm()) {
            return;
        }
        // 简化实现：这里只是校验备份是否存在
        adminDataMapper.getBackupById(dto.getBackupId());
    }

    @Override
    public DataArchiveConfigVO getArchiveConfig() {
        ArchiveConfig config = adminDataMapper.getArchiveConfig();
        if (config == null) {
            return null;
        }
        DataArchiveConfigVO vo = new DataArchiveConfigVO();
        vo.setEnabled(config.getEnabled() != null && config.getEnabled() == 1);
        vo.setCycle(config.getCycle());
        vo.setKeepYears(config.getKeepYears());
        vo.setArchivePath(config.getArchivePath());
        return vo;
    }

    @Override
    public void saveArchiveConfig(ArchiveConfigDTO dto) {
        ArchiveConfig config = new ArchiveConfig();
        config.setId(1);
        config.setEnabled(Boolean.TRUE.equals(dto.getEnabled()) ? 1 : 0);
        config.setCycle(dto.getCycle());
        config.setKeepYears(dto.getKeepYears());
        config.setArchivePath(dto.getArchivePath());
        adminDataMapper.upsertArchiveConfig(config);
    }

    @Override
    public PageResult listArchiveHistory(ArchiveHistoryQueryDTO query) {
        if (query.getPage() == null || query.getPageSize() == null) {
            query.setPage(1);
            query.setPageSize(10);
        }
        PageHelper.startPage(query.getPage(), query.getPageSize());
        Page<ArchiveHistory> page = adminDataMapper.pageArchiveHistory(query);
        List<ArchiveHistoryItemVO> list = page.getResult().stream().map(this::toArchiveHistoryVO).collect(Collectors.toList());
        return new PageResult(page.getTotal(), list);
    }

    private BackupItemVO toBackupVO(BackupRecord record) {
        BackupItemVO vo = new BackupItemVO();
        BeanUtils.copyProperties(record, vo);
        if (record.getCreatedAt() != null) {
            vo.setCreatedAt(record.getCreatedAt().format(DT_FMT));
        }
        return vo;
    }

    private ArchiveHistoryItemVO toArchiveHistoryVO(ArchiveHistory entity) {
        ArchiveHistoryItemVO vo = new ArchiveHistoryItemVO();
        BeanUtils.copyProperties(entity, vo);
        if (entity.getCreatedAt() != null) {
            vo.setCreatedAt(entity.getCreatedAt().format(DT_FMT));
        }
        return vo;
    }
}


