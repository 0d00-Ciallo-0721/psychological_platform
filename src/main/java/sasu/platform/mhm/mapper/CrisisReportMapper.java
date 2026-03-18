package sasu.platform.mhm.mapper;

import org.apache.ibatis.annotations.Mapper;
import sasu.platform.mhm.pojo.VO.CrisisReportVO;
import sasu.platform.mhm.pojo.dto.CrisisReportCreateDTO;
import sasu.platform.mhm.pojo.dto.CrisistLevelDTO;
import sasu.platform.mhm.pojo.dto.CrisisApprovalQueryDTO;
import sasu.platform.mhm.pojo.vo.CrisisApprovalVO;

import java.util.List;

@Mapper
public interface CrisisReportMapper {
    boolean report(CrisisReportCreateDTO crisisReportCreateDTO);

    List<CrisisReportVO> getCrisisList(String crisisLevel, String reportStatus, String college);

    boolean setLevel(CrisistLevelDTO crisistLevelDTO);

    /**
     * 危机审批列表
     */
    List<CrisisApprovalVO> selectCrisisApprovalList(CrisisApprovalQueryDTO query);

    /**
     * 危机审批通过
     */
    int approveCrisis(String id, String reviewerId, String comment);

    /**
     * 危机审批驳回
     */
    int rejectCrisis(String id, String reviewerId, String comment);
}
