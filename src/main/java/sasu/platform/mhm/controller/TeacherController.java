package sasu.platform.mhm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sasu.platform.mhm.pojo.dto.CounselorListQueryDTO;
import sasu.platform.mhm.pojo.dto.TeacherInboxQueryDTO;
import sasu.platform.mhm.pojo.dto.TeacherReplyDTO;
import sasu.platform.mhm.pojo.vo.CounselorVO;
import sasu.platform.mhm.pojo.vo.TeacherMessageVO;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.service.TeacherService;

import java.util.List;

/**
 * 辅导员/教师模块接口
 */
@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    /**
     * 收件箱
     * GET /api/teacher/message/inbox
     */
    @GetMapping("/message/inbox")
    public R<java.util.List<TeacherMessageVO>> inbox(TeacherInboxQueryDTO query) {
        java.util.List<TeacherMessageVO> list = teacherService.inbox(query);
        return R.success(list);
    }

    /**
     * 回复留言
     * POST /api/teacher/message/reply
     */
    @PostMapping("/message/reply")
    public R<Void> reply(@RequestBody TeacherReplyDTO dto) {
        teacherService.reply(dto);
        return R.success();
    }

    /**
     * 辅导员列表（领导端）
     * GET /api/teacher/counselor/list
     */
    @GetMapping("/counselor/list")
    public R<List<CounselorVO>> counselorList(CounselorListQueryDTO query) {
        List<CounselorVO> list = teacherService.counselorList(query);
        return R.success(list);
    }
}


