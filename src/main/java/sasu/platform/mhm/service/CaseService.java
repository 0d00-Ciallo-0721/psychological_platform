package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.CaseCreateDTO;
import sasu.platform.mhm.pojo.dto.CaseListQueryDTO;
import sasu.platform.mhm.pojo.dto.CaseUpdateDTO;
import sasu.platform.mhm.pojo.vo.CaseVO;

import java.util.List;
import java.util.Map;

/**
 * 个案服务接口
 */
public interface CaseService {

    /**
     * 查询个案列表
     */
    PageResult list(CaseListQueryDTO query);

    /**
     * 查询个案详情
     */
    CaseVO detail(String caseId);

    /**
     * 创建个案
     */
    CaseVO create(CaseCreateDTO dto);

    /**
     * 更新个案
     */
    CaseVO update(CaseUpdateDTO dto);

    /**
     * 查询个案统计
     */
    List<Map<String, Object>> stats(String counselorId);
}

