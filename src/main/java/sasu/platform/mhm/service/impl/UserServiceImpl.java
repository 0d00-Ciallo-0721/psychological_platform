package sasu.platform.mhm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sasu.platform.mhm.mapper.UserMapper;
import sasu.platform.mhm.pojo.dto.ChangePasswordDTO;
import sasu.platform.mhm.pojo.dto.ResetPasswordDTO;
import sasu.platform.mhm.pojo.entity.User;
import sasu.platform.mhm.pojo.entity.UserDetailsImpl;
import sasu.platform.mhm.service.UserService;

/**
 * 用户服务实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    /**
     * 获取当前用户ID
     */
    private String currentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            return userDetails.getUser().getUserId();
        }
        return null;
    }

    @Override
    public void changePassword(ChangePasswordDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        if (!StringUtils.hasText(dto.getOldPassword())) {
            throw new IllegalArgumentException("旧密码不能为空");
        }
        if (!StringUtils.hasText(dto.getNewPassword())) {
            throw new IllegalArgumentException("新密码不能为空");
        }
        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("两次密码输入不一致");
        }

        String userId = currentUserId();
        if (!StringUtils.hasText(userId)) {
            throw new IllegalArgumentException("未登录");
        }

        // 查询当前用户
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        // 验证旧密码
        if (passwordEncoder == null || !passwordEncoder.matches(dto.getOldPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("旧密码不正确");
        }
        // 更新新密码
        String newHash = passwordEncoder.encode(dto.getNewPassword());
        user.setPasswordHash(newHash);
        userMapper.updateById(user);
    }

    @Override
    public void resetPassword(ResetPasswordDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        if (!StringUtils.hasText(dto.getUsername())) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (!StringUtils.hasText(dto.getVerificationCode())) {
            throw new IllegalArgumentException("验证码不能为空");
        }
        if (!StringUtils.hasText(dto.getNewPassword())) {
            throw new IllegalArgumentException("新密码不能为空");
        }
        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("两次密码输入不一致");
        }

        // 这里简化验证码校验逻辑，实际应接入短信/邮箱验证码服务
        // 假定前端已完成验证，这里直接允许重置

        User user = userMapper.selectByUsername(dto.getUsername());
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        String newHash = passwordEncoder.encode(dto.getNewPassword());
        user.setPasswordHash(newHash);
        userMapper.updateById(user);
    }

    @Override
    public void sendVerificationCode(String phone) {
        if (!StringUtils.hasText(phone)) {
            throw new IllegalArgumentException("手机号不能为空");
        }

        // 这里暂不接第三方短信服务，仅预留接口
        // 实际项目中应在此集成短信/邮件服务并缓存验证码
    }
}

