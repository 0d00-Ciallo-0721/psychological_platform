package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.dto.ConsentConfigDTO;
import sasu.platform.mhm.pojo.dto.PreAssessmentConfigDTO;
import sasu.platform.mhm.pojo.dto.VisitFormConfigDTO;

/**
 * 配置服务接口
 */
public interface ConfigService {

    /**
     * 获取来访登记表配置
     */
    VisitFormConfigDTO getVisitFormConfig();

    /**
     * 保存来访登记表配置
     */
    void saveVisitFormConfig(VisitFormConfigDTO config);

    /**
     * 获取前测量表配置
     */
    PreAssessmentConfigDTO getPreAssessmentConfig();

    /**
     * 保存前测量表配置
     */
    void savePreAssessmentConfig(PreAssessmentConfigDTO config);

    /**
     * 获取知情同意书配置
     */
    ConsentConfigDTO getConsentConfig();

    /**
     * 保存知情同意书配置
     */
    void saveConsentConfig(ConsentConfigDTO config);
}

