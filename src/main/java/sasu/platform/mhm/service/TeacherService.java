package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.dto.CounselorListQueryDTO;
import sasu.platform.mhm.pojo.dto.TeacherInboxQueryDTO;
import sasu.platform.mhm.pojo.dto.TeacherReplyDTO;
import sasu.platform.mhm.pojo.vo.CounselorVO;
import sasu.platform.mhm.pojo.vo.TeacherMessageVO;

import java.util.List;

public interface TeacherService {

    /**
     * 收件箱
     */
    java.util.List<TeacherMessageVO> inbox(TeacherInboxQueryDTO query);

    /**
     * 回复留言
     */
    void reply(TeacherReplyDTO dto);

    /**
     * 辅导员列表（领导端）
     */
    List<CounselorVO> counselorList(CounselorListQueryDTO query);
}


