package sasu.platform.mhm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sasu.platform.mhm.filter.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * 配置Spring Security的过滤器链，定义安全策略和访问控制规则
     *
     * @param http HttpSecurity配置对象，用于构建安全过滤器链
     * @return 配置好的SecurityFilterChain实例
     * @throws Exception 配置过程中可能抛出的异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // 修改了这里
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        // 👇 在这里增加了放行 Swagger 和 OpenAPI 接口文档的路径
                        .requestMatchers(
                                "/api/common/login", 
                                "/api/common/getVerificationCode",
                                "/api/common/health",
                                "/api/register",       // 前面加过的注册接口
                                "/api/auth/**",        
                                "/api/cms/**",
                                "/api/activity/**",
                                "/api/notice/**",      // 👈 新增：放行首页公告接口
                                "/api/home/**",        // 👈 新增：放行首页聚合数据接口 (防患于未然)
                                "/v3/api-docs/**",     
                                "/swagger-ui/**",      
                                "/swagger-ui.html"       // 放行Swagger入口页面
                        ).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}