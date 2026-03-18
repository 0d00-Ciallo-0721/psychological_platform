package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 修改密码DTO
 */
@Data
public class ChangePasswordDTO {
    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 确认密码
     */
    private String confirmPassword;
}

