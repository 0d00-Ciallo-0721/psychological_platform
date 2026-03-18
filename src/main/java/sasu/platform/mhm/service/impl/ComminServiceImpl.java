package sasu.platform.mhm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sasu.platform.mhm.mapper.UserMapper;
import sasu.platform.mhm.pojo.entity.User;
import sasu.platform.mhm.service.CommonService;
import sasu.platform.mhm.util.CaptchaUtil;
import sasu.platform.mhm.util.JwtUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ComminServiceImpl implements CommonService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;
    /**
     * 生成验证码
     *
     * @return 返回验证码图片的字节数组
     */
    public Map<String,byte[]> verificationCode() {
        byte[] bytes = new byte[0];
        String code =  "";
        Map<String,byte[]> map = new HashMap<>();
        try {
            code = CaptchaUtil.generateCaptchaCode(4);
            bytes = CaptchaUtil.generateCaptchaImage(code, 100, 40);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //生成一个UUID作为验证码的key
        String key = java.util.UUID.randomUUID().toString();
        //将生成的验证码存储到redis中并设置衰减时间
        redisTemplate.opsForValue().set(key, code, 60 , TimeUnit.SECONDS);
        map.put(key, bytes);
        return map;

    }

    /**
     * 用户登录功能
     *
     * 该方法实现用户登录验证流程，包括验证码校验、用户信息验证、
     * 账号状态检查、密码验证以及JWT令牌生成等功能。
     *
     * @param username 用户名
     * @param password 用户密码
     * @param verificationCode 验证码
     * @param key Redis中验证码对应的键值
     * @return 返回一个Map，键为角色名称，值为JWT令牌；如果验证失败，键为"false"，值为错误信息
     */
    @Override
    public Map<String, String> login(String username, String password, String verificationCode, String key) {
        // 1. 验证验证码
        String code = (String) redisTemplate.opsForValue().get(key);
        if (!verificationCode.equals(code)) {
            return Map.of("false", "验证码错误");
        }
        // 2. 验证密码
        //从数据库中获取用户信息,根据用户名查询
        User userInfo = userMapper.selectByUsername(username);
        if (userInfo == null) {
            return Map.of("false", "用户名不存在");
        }
        // 3. 校验账号状态
        if (userInfo.getAccountStatus() != null && userInfo.getAccountStatus() != 1) {
            return Map.of("false", "账号已被禁用或锁定");
        }

        // 4. 校验密码（假设使用 BCrypt 加密）
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, userInfo.getPasswordHash())) {
            return Map.of("false", "用户名或密码错误");
        }
        // 5. 生成 JWT
        String token = JwtUtil.createJWT(userInfo.getUserId(),userInfo.getRole_code()); // 或者 username

        // 6. 更新最近登录时间等
        userMapper.updateById(userInfo);
        // 7. 返回 token（这里建议换成自定义返回对象，而不是 Map<Boolean,String>）
        return Map.of(userInfo.getRole_name(), token);
    }
}
