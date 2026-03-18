package sasu.platform.mhm.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sasu.platform.mhm.pojo.dto.CaseListQueryDTO;
import sasu.platform.mhm.pojo.entity.ConsultationCase;
import sasu.platform.mhm.pojo.vo.CaseVO;

/**
 * 个案Mapper
 */
@Mapper
public interface CaseMapper {

    /**
     * 查询个案列表
     */
    Page<CaseVO> selectCaseList(@Param("query") CaseListQueryDTO query);

    /**
     * 查询个案详情
     */
    CaseVO selectCaseById(@Param("caseId") String caseId);

    /**
     * 插入个案
     */
    int insertCase(ConsultationCase consultationCase);

    /**
     * 更新个案
     */
    int updateCase(ConsultationCase consultationCase);

    /**
     * 查询个案统计
     */
    java.util.List<java.util.Map<String, Object>> selectCaseStats(@Param("counselorId") String counselorId);
}

