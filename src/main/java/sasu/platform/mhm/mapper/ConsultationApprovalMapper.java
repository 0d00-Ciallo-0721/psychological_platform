package sasu.platform.mhm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sasu.platform.mhm.pojo.dto.ConsultationApprovalQueryDTO;
import sasu.platform.mhm.pojo.vo.ConsultationApprovalVO;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ConsultationApprovalMapper {

    /**
     * 长程咨询申请审批列表
     */
    List<ConsultationApprovalVO> selectConsultationApprovalList(ConsultationApprovalQueryDTO query);

    /**
     * 审批通过
     */
    int approve(@Param("id") String id,
                @Param("approverId") String approverId,
                @Param("comment") String comment,
                @Param("approvedSessions") Integer approvedSessions,
                @Param("approvedFrequency") String approvedFrequency,
                @Param("startDate") LocalDate startDate,
                @Param("endDate") LocalDate endDate);

    /**
     * 审批驳回
     */
    int reject(@Param("id") String id,
               @Param("approverId") String approverId,
               @Param("comment") String comment);
}


