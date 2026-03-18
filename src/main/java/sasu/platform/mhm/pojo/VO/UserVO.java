package sasu.platform.mhm.pojo.vo;

import lombok.Data;

@Data
public class UserVO {
    private String userId;
    private String username;
    private String realName;
    private String gender;
    private String avatarUrl;
    private String accountStatus;
}
