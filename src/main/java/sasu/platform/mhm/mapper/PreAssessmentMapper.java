package sasu.platform.mhm.mapper;

import org.apache.ibatis.annotations.Mapper;
import sasu.platform.mhm.pojo.entity.PreAssessment;

@Mapper
public interface PreAssessmentMapper {
    boolean insert(PreAssessment preAssessment);
}


