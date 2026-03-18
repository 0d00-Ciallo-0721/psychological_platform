package sasu.platform.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sasu.platform.mhm.pojo.common.R;
import sasu.platform.mhm.pojo.dto.ChangePasswordDTO;
import sasu.platform.mhm.pojo.dto.ResetPasswordDTO;
import sasu.platform.mhm.pojo.entity.UserDetailsImpl;
import sasu.platform.mhm.pojo.vo.UserVO;
import sasu.platform.mhm.service.UserService;

/**
 * 用户相关接口（占位实现，方便前端联调）
 *
 * 说明：
 * - 目前项目中还没有完整的用户实体与持久化，这里先返回一个静态/占位的用户信息
 * - 后续可以接入实际的用户表和 Spring Security 的认证信息
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired(required = false)
    private UserService userService;

    /**
     * 获取当前登录用户信息
     */
    @GetMapping("/info")
    public R<UserVO> info() {
        log.info("获取当前用户信息占位实现");
        UserVO vo = new UserVO();
        vo.setUserId("mock-user");
        vo.setUsername("mock");
        vo.setRealName("示例用户");
        vo.setAccountStatus("ACTIVE");
        return R.success(vo);
    }

    /**
     * 更新当前用户信息
     *
     * 目前仅作回显，不做实际更新。
     */
    @PutMapping("/update")
    public R<UserVO> update(@RequestBody UserVO body) {
        log.info("更新用户信息占位实现: {}", body);
        return R.success(body);
    }

    /**
     * 修改密码
     */
    @PostMapping("/change-password")
    public R<String> changePassword(@RequestBody ChangePasswordDTO dto) {
        log.info("修改密码");
        if (userService != null) {
            userService.changePassword(dto);
            return R.success("修改成功");
        }
        return R.error("功能暂未实现");
    }

    /**
     * 重置密码
     */
    @PostMapping("/reset-password")
    public R<String> resetPassword(@RequestBody ResetPasswordDTO dto) {
        log.info("重置密码: {}", dto.getUsername());
        if (userService != null) {
            userService.resetPassword(dto);
            return R.success("重置成功");
        }
        return R.error("功能暂未实现");
    }

    /**
     * 发送验证码（用于找回密码）
     */
    @PostMapping("/send-code")
    public R<String> sendCode(@RequestParam String phone) {
        log.info("发送验证码: {}", phone);
        if (userService != null) {
            userService.sendVerificationCode(phone);
            return R.success("发送成功");
        }
        return R.error("功能暂未实现");
    }
}


