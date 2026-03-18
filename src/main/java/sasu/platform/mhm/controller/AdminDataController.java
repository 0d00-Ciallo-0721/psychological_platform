package sasu.platform.mhm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.*;
import sasu.platform.mhm.pojo.vo.ArchiveHistoryItemVO;
import sasu.platform.mhm.pojo.vo.BackupItemVO;
import sasu.platform.mhm.pojo.vo.DataArchiveConfigVO;
import sasu.platform.mhm.service.AdminDataService;

/**
 * 管理端-数据管理相关接口
 */
@RestController
@RequestMapping("/api/admin/data")
@RequiredArgsConstructor
public class AdminDataController {

    private final AdminDataService adminDataService;

    @GetMapping("/backup/list")
    public R<PageResult> backupList(BackupListQueryDTO query) {
        PageResult page = adminDataService.listBackup(query);
        return R.success(page);
    }

    @PostMapping("/backup")
    public R<BackupItemVO> createBackup(@RequestBody BackupCreateDTO dto) {
        BackupItemVO vo = adminDataService.createBackup(dto);
        return R.success(vo);
    }

    @PostMapping("/restore")
    public R<Void> restore(@RequestBody BackupRestoreDTO dto) {
        adminDataService.restoreBackup(dto);
        return R.success();
    }

    @GetMapping("/archive/config")
    public R<DataArchiveConfigVO> getArchiveConfig() {
        DataArchiveConfigVO vo = adminDataService.getArchiveConfig();
        return R.success(vo);
    }

    @PostMapping("/archive/config")
    public R<Void> saveArchiveConfig(@RequestBody ArchiveConfigDTO dto) {
        adminDataService.saveArchiveConfig(dto);
        return R.success();
    }

    @GetMapping("/archive/history")
    public R<PageResult> archiveHistory(ArchiveHistoryQueryDTO query) {
        PageResult page = adminDataService.listArchiveHistory(query);
        return R.success(page);
    }
}

