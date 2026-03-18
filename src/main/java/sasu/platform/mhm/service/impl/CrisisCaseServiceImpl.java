package sasu.platform.mhm.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sasu.platform.mhm.mapper.CrisisReportMapper;
import sasu.platform.mhm.pojo.VO.CrisisReportVO;
import sasu.platform.mhm.pojo.VO.CrisistLevelVO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.CrisisListQueryDTO;
import sasu.platform.mhm.pojo.dto.CrisisReportCreateDTO;
import sasu.platform.mhm.pojo.dto.CrisistLevelDTO;
import sasu.platform.mhm.service.CrisisCaseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrisisCaseServiceImpl implements CrisisCaseService {

    @Autowired
    CrisisReportMapper crisisReportMapper;

    @Override
    public boolean report(CrisisReportCreateDTO crisisReportCreateDTO) {

        //设置唯一ID
        crisisReportCreateDTO.setReportId(IdUtil.randomUUID());

        crisisReportMapper.report(crisisReportCreateDTO);
        return true;
    }

    @Override
    public PageResult getCrisisList(Integer page, Integer pageSize, CrisisListQueryDTO crisisListQueryDTO) {
        // 设置分页参数
        PageHelper.startPage(page,pageSize);
        // 从DTO中获取查询参数，传递给Mapper层
        List<CrisisReportVO> empList = crisisReportMapper.getCrisisList(crisisListQueryDTO.getCrisis_level(),crisisListQueryDTO.getReport_status(),crisisListQueryDTO.getCollege());
        // 获取分页结果
        Page<CrisisReportVO> p = (Page<CrisisReportVO>) empList;
        // 封装 PageResult
        PageResult pageResult = new PageResult(p.getTotal(),p.getResult());
        return pageResult;
    }

    @Override
    public CrisistLevelVO setLevel(CrisistLevelDTO crisistLevelDTO) {
        if (crisisReportMapper.setLevel(crisistLevelDTO)){
            CrisistLevelVO result = new CrisistLevelVO();
            result.setCrisisLevel(crisistLevelDTO.getCrisisLevel());
            result.setReportId(crisistLevelDTO.getReportId());
            return result;
        }
        return null;
    }
}

