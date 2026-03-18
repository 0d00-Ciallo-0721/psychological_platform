package sasu.platform.mhm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sasu.platform.mhm.pojo.entity.PreAssessmentQuestion;
import sasu.platform.mhm.pojo.entity.PreAssessmentTemplate;

import java.util.List;

/**
 * 前测量表模板与题目 Mapper
 *
 * 说明：
 * - 该 Mapper 提供 AssessmentServiceImpl 所需的最小方法集
 * - 对应的表结构见 db/migration/
 */
@Mapper
public interface PreAssessmentTemplateMapper {

    /**
     * 根据模板ID查询模板
     */
    PreAssessmentTemplate getTemplateById(@Param("templateId") String templateId);

    /**
     * 根据模板ID查询题目列表
     */
    List<PreAssessmentQuestion> listQuestionsByTemplateId(@Param("templateId") String templateId);
}


