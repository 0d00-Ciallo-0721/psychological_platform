package sasu.platform.mhm.service.impl;

import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sasu.platform.mhm.mapper.ConfigMapper;
import sasu.platform.mhm.pojo.dto.ConsentConfigDTO;
import sasu.platform.mhm.pojo.dto.PreAssessmentConfigDTO;
import sasu.platform.mhm.pojo.dto.VisitFormConfigDTO;
import sasu.platform.mhm.pojo.entity.SystemConfig;
import sasu.platform.mhm.pojo.entity.UserDetailsImpl;
import sasu.platform.mhm.service.ConfigService;

/**
 * 配置服务实现
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigMapper configMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String VISIT_FORM_KEY = "visit_form_config";
    private static final String PRE_ASSESSMENT_KEY = "pre_assessment_config";
    private static final String CONSENT_KEY = "consent_config";

    /**
     * 获取当前用户ID
     */
    private String currentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            return userDetails.getUser().getUserId();
        }
        return null;
    }

    @Override
    public VisitFormConfigDTO getVisitFormConfig() {
        SystemConfig config = configMapper.selectByKey(VISIT_FORM_KEY);
        if (config == null || !StringUtils.hasText(config.getConfigValue())) {
            // 返回默认配置
            VisitFormConfigDTO defaultConfig = new VisitFormConfigDTO();
            defaultConfig.setEnabled(true);
            return defaultConfig;
        }
        try {
            return objectMapper.readValue(config.getConfigValue(), VisitFormConfigDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("解析来访登记表配置失败: " + e.getMessage());
        }
    }

    @Override
    public void saveVisitFormConfig(VisitFormConfigDTO config) {
        if (config == null) {
            throw new IllegalArgumentException("配置内容不能为空");
        }
        String userId = currentUserId();
        String configValue = null;
        try {
            configValue = objectMapper.writeValueAsString(config);
        } catch (Exception e) {
            throw new RuntimeException("序列化配置失败: " + e.getMessage());
        }

        SystemConfig existing = configMapper.selectByKey(VISIT_FORM_KEY);
        if (existing != null) {
            existing.setConfigValue(configValue);
            existing.setUpdatedAt(java.time.LocalDateTime.now());
            configMapper.updateConfig(existing);
        } else {
            SystemConfig newConfig = new SystemConfig();
            newConfig.setConfigId(IdUtil.randomUUID());
            newConfig.setConfigKey(VISIT_FORM_KEY);
            newConfig.setConfigValue(configValue);
            newConfig.setConfigType("VISIT_FORM");
            newConfig.setDescription("来访登记表配置");
            newConfig.setEnabled(1);
            newConfig.setCreatedBy(userId);
            configMapper.insertConfig(newConfig);
        }
    }

    @Override
    public PreAssessmentConfigDTO getPreAssessmentConfig() {
        SystemConfig config = configMapper.selectByKey(PRE_ASSESSMENT_KEY);
        if (config == null || !StringUtils.hasText(config.getConfigValue())) {
            // 返回默认配置
            return new PreAssessmentConfigDTO();
        }
        try {
            return objectMapper.readValue(config.getConfigValue(), PreAssessmentConfigDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("解析前测量表配置失败: " + e.getMessage());
        }
    }

    @Override
    public void savePreAssessmentConfig(PreAssessmentConfigDTO config) {
        if (config == null) {
            throw new IllegalArgumentException("配置内容不能为空");
        }
        String userId = currentUserId();
        String configValue = null;
        try {
            configValue = objectMapper.writeValueAsString(config);
        } catch (Exception e) {
            throw new RuntimeException("序列化配置失败: " + e.getMessage());
        }

        SystemConfig existing = configMapper.selectByKey(PRE_ASSESSMENT_KEY);
        if (existing != null) {
            existing.setConfigValue(configValue);
            existing.setUpdatedAt(java.time.LocalDateTime.now());
            configMapper.updateConfig(existing);
        } else {
            SystemConfig newConfig = new SystemConfig();
            newConfig.setConfigId(IdUtil.randomUUID());
            newConfig.setConfigKey(PRE_ASSESSMENT_KEY);
            newConfig.setConfigValue(configValue);
            newConfig.setConfigType("PRE_ASSESSMENT");
            newConfig.setDescription("前测量表配置");
            newConfig.setEnabled(1);
            newConfig.setCreatedBy(userId);
            configMapper.insertConfig(newConfig);
        }
    }

    @Override
    public ConsentConfigDTO getConsentConfig() {
        SystemConfig config = configMapper.selectByKey(CONSENT_KEY);
        if (config == null || !StringUtils.hasText(config.getConfigValue())) {
            // 返回默认配置
            ConsentConfigDTO defaultConfig = new ConsentConfigDTO();
            defaultConfig.setEnabled(true);
            defaultConfig.setForce(true);
            return defaultConfig;
        }
        try {
            return objectMapper.readValue(config.getConfigValue(), ConsentConfigDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("解析知情同意书配置失败: " + e.getMessage());
        }
    }

    @Override
    public void saveConsentConfig(ConsentConfigDTO config) {
        if (config == null) {
            throw new IllegalArgumentException("配置内容不能为空");
        }
        String userId = currentUserId();
        String configValue = null;
        try {
            configValue = objectMapper.writeValueAsString(config);
        } catch (Exception e) {
            throw new RuntimeException("序列化配置失败: " + e.getMessage());
        }

        SystemConfig existing = configMapper.selectByKey(CONSENT_KEY);
        if (existing != null) {
            existing.setConfigValue(configValue);
            existing.setUpdatedAt(java.time.LocalDateTime.now());
            configMapper.updateConfig(existing);
        } else {
            SystemConfig newConfig = new SystemConfig();
            newConfig.setConfigId(IdUtil.randomUUID());
            newConfig.setConfigKey(CONSENT_KEY);
            newConfig.setConfigValue(configValue);
            newConfig.setConfigType("CONSENT");
            newConfig.setDescription("知情同意书配置");
            newConfig.setEnabled(1);
            newConfig.setCreatedBy(userId);
            configMapper.insertConfig(newConfig);
        }
    }
}

