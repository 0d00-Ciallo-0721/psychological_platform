package sasu.platform.mhm.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类，用于生成、解析和验证JWT令牌
 * 使用@Component注解标记为Spring组件，便于其他组件注入使用
 */
@Component
public class JwtUtil {
    // JWT令牌有效期常量，设置为14天（毫秒）
    public static final long JWT_TTL = 60 * 60 * 1000L * 24 * 14;  // 有效期14天
    // JWT签名密钥，用于生成和验证JWT令牌的签名
    public static final String JWT_KEY = "SDFGjhdsfalshdfHFdsjkdsfds121232131afasdfac";

    /**
     * 生成UUID字符串，去除连字符
     *
     * @return 不带连字符的UUID字符串
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 创建JWT令牌
     *
     * @param subject 令牌主题信息，通常为用户标识或相关信息
     * @return 生成的JWT令牌字符串
     */
    public static String createJWT(String subject,String role_code) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID(),role_code);
        return builder.compact();
    }

    /**
     * 构建JWT令牌生成器，用于配置JWT令牌的各项属性
     *
     * @param subject 令牌主题信息，通常为用户标识或相关主体信息
     * @param ttlMillis 令牌有效期（毫秒），如果为null则使用默认有效期
     * @param uuid 令牌的唯一标识符
     * @return 配置好的JWT构建器，可用于生成最终的JWT令牌字符串
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid,String role_code) {
        // 设置签名算法为HS256
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成JWT验证密钥
        SecretKey secretKey = generalKey();
        // 获取当前时间戳
        long nowMillis = System.currentTimeMillis();
        // 转换为Date对象
        Date now = new Date(nowMillis);
        // 如果未指定有效期，使用默认有效期
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }

        // 计算过期时间戳
        long expMillis = nowMillis + ttlMillis;
        // 转换为Date对象
        Date expDate = new Date(expMillis);
        // 构建JWT令牌，设置各项属性
        return Jwts.builder()
                .id(uuid)                    // 设置令牌ID
                .subject(subject)            // 设置令牌主题
                .claim("role_code",role_code)  //设置角色信息
                .issuer("sg")                // 设置签发者
                .issuedAt(now)               // 设置签发时间
                .signWith(secretKey)         // 设置签名密钥
                .expiration(expDate);        // 设置过期时间
    }

    /**
     * 生成JWT验证密钥
     *
     * @return 用于JWT签名和验证的SecretKey对象
     */
    public static SecretKey generalKey() {
        // 将Base64编码的密钥字符串解码为字节数组
        byte[] encodeKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        // 使用解码后的字节数组创建SecretKey对象
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "HmacSHA256");
    }

    /**
     * 解析JWT令牌，提取其中的声明信息
     *
     * @param jwt 要解析的JWT令牌字符串
     * @return 解析后的Claims对象，包含令牌中的所有声明信息
     * @throws Exception 如果令牌无效、签名验证失败或解析过程中出现其他错误
     */
    public static Claims parseJWT(String jwt) throws Exception {
        // 生成JWT验证所需的密钥
        SecretKey secretKey = generalKey();
        // 使用密钥验证并解析JWT令牌，返回其中的声明信息
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}

