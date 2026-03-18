package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 重置密码DTO
 */
@Data
public class ResetPasswordDTO {
    /**
     * 用户名或手机号
     */
    private String username;

    /**
     * 验证码
     */
    private String verificationCode;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 确认密码
     */
    private String confirmPassword;
}

