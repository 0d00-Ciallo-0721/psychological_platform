package sasu.platform.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.ConsentConfigDTO;
import sasu.platform.mhm.pojo.dto.PreAssessmentConfigDTO;
import sasu.platform.mhm.pojo.dto.VisitFormConfigDTO;
import sasu.platform.mhm.service.ConfigService;

/**
 * 配置控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    /**
     * 获取来访登记表配置
     */
    @GetMapping("/visit-form")
    public R<VisitFormConfigDTO> getVisitFormConfig() {
        log.info("获取来访登记表配置");
        VisitFormConfigDTO config = configService.getVisitFormConfig();
        return R.success(config);
    }

    /**
     * 保存来访登记表配置
     */
    @PostMapping("/visit-form")
    public R<String> saveVisitFormConfig(@RequestBody VisitFormConfigDTO config) {
        log.info("保存来访登记表配置: {}", config);
        configService.saveVisitFormConfig(config);
        return R.success("保存成功");
    }

    /**
     * 获取前测量表配置
     */
    @GetMapping("/pre-assessment")
    public R<PreAssessmentConfigDTO> getPreAssessmentConfig() {
        log.info("获取前测量表配置");
        PreAssessmentConfigDTO config = configService.getPreAssessmentConfig();
        return R.success(config);
    }

    /**
     * 保存前测量表配置
     */
    @PostMapping("/pre-assessment")
    public R<String> savePreAssessmentConfig(@RequestBody PreAssessmentConfigDTO config) {
        log.info("保存前测量表配置: {}", config);
        configService.savePreAssessmentConfig(config);
        return R.success("保存成功");
    }

    /**
     * 获取知情同意书配置
     */
    @GetMapping("/consent")
    public R<ConsentConfigDTO> getConsentConfig() {
        log.info("获取知情同意书配置");
        ConsentConfigDTO config = configService.getConsentConfig();
        return R.success(config);
    }

    /**
     * 保存知情同意书配置
     */
    @PostMapping("/consent")
    public R<String> saveConsentConfig(@RequestBody ConsentConfigDTO config) {
        log.info("保存知情同意书配置: {}", config);
        configService.saveConsentConfig(config);
        return R.success("保存成功");
    }
}

