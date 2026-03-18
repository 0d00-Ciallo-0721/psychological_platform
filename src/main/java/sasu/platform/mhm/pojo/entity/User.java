package sasu.platform.mhm.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 角色编码
     */
    private String role_code;

    /**
     * 角色名称
     */
    private String role_name;

    /**
     * 密码哈希值
     */
    private String passwordHash;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别: 0-未知, 1-男, 2-女
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private LocalDate birthDate;

    /**
     * 头像URL地址
     */
    private String avatarUrl;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 微信OpenID
     */
    private String wechatOpenid;

    /**
     * 微信UnionID
     */
    private String wechatUnionid;

    /**
     * 账号状态: 0-禁用, 1-正常, 2-锁定
     */
    private Integer accountStatus = 1;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 最后登录IP地址
     */
    private String lastLoginIp;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt = LocalDateTime.now();

    /**
     * 删除时间(软删除标记)
     */
    private LocalDateTime deletedAt;

}
