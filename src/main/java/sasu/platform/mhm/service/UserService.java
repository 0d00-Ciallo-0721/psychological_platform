package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.dto.ChangePasswordDTO;
import sasu.platform.mhm.pojo.dto.ResetPasswordDTO;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 修改密码
     */
    void changePassword(ChangePasswordDTO dto);

    /**
     * 重置密码
     */
    void resetPassword(ResetPasswordDTO dto);

    /**
     * 发送验证码
     */
    void sendVerificationCode(String phone);
}

