package sasu.platform.mhm.filter;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sasu.platform.mhm.mapper.UserMapper;
import sasu.platform.mhm.pojo.entity.User;
import sasu.platform.mhm.pojo.entity.UserDetailsImpl;
import sasu.platform.mhm.util.JwtUtil;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserMapper userMapper;

    /**
     * 执行JWT认证过滤器，处理请求的JWT令牌验证和用户认证
     *
     * @param request HTTP请求对象，包含客户端请求信息
     * @param response HTTP响应对象，用于向客户端返回响应
     * @param filterChain 过滤器链，用于将请求传递给下一个过滤器
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        // 从请求头获取Authorization字段
        String token = request.getHeader("Authorization");

        // 如果token为空或不是Bearer开头，直接放行
        if (!StringUtils.hasText(token) || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 去掉Bearer前缀，获取实际的token
        token = token.substring(7);

        String userid;
        String role_code;
        try {
            // 解析JWT token获取用户信息
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
            role_code= claims.get("role_code").toString();
            log.info("用户id为：{},用户的角色为：{}", userid, role_code);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        User user = new User();
        user.setUserId(userid);
        user.setRole_code(role_code);
        // 构建用户详情对象
        UserDetailsImpl loginUser = new UserDetailsImpl(user);
        // 创建认证令牌
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);

        // 将认证信息设置到安全上下文中
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 继续过滤器链
        filterChain.doFilter(request, response);
    }
}
