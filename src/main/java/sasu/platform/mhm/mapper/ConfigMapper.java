package sasu.platform.mhm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sasu.platform.mhm.pojo.entity.SystemConfig;

/**
 * 配置Mapper
 */
@Mapper
public interface ConfigMapper {

    /**
     * 根据配置键查询配置
     */
    SystemConfig selectByKey(@Param("configKey") String configKey);

    /**
     * 根据配置类型查询配置
     */
    SystemConfig selectByType(@Param("configType") String configType);

    /**
     * 插入配置
     */
    int insertConfig(SystemConfig config);

    /**
     * 更新配置
     */
    int updateConfig(SystemConfig config);

    /**
     * 删除配置
     */
    int deleteConfig(@Param("configKey") String configKey);
}

