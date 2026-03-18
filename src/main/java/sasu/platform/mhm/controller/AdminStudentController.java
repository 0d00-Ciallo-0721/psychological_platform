package sasu.platform.mhm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.AdminStudentQueryDTO;
import sasu.platform.mhm.pojo.vo.AdminStudentVO;
import sasu.platform.mhm.service.AdminStudentService;

/**
 * 管理端-学生管理接口
 */
@RestController
@RequestMapping("/api/admin/student")
@RequiredArgsConstructor
public class AdminStudentController {

    private final AdminStudentService adminStudentService;

    @GetMapping("/list")
    public R<PageResult> list(AdminStudentQueryDTO query) {
        PageResult page = adminStudentService.page(query);
        return R.success(page);
    }

    @GetMapping("/detail")
    public R<AdminStudentVO> detail(@RequestParam("studentId") String studentId) {
        AdminStudentVO vo = adminStudentService.detail(studentId);
        return R.success(vo);
    }

    @GetMapping("/profile")
    public R<AdminStudentVO> profile(@RequestParam("studentId") String studentId) {
        AdminStudentVO vo = adminStudentService.detail(studentId);
        return R.success(vo);
    }
}

