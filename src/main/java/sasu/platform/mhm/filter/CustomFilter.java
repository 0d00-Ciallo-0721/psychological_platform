package sasu.platform.mhm.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CustomFilter extends OncePerRequestFilter {

    /**
     * .
     * 执行自定义过滤逻辑的内部方法
     *
     * 该方法在每个请求中执行一次，用于记录请求URI日志并继续执行过滤链。
     * 主要功能包括：记录请求的URI信息，然后将请求传递给下一个过滤器或目标资源。
     *
     * @param request HttpServletRequest对象，包含客户端请求信息
     * @param response HttpServletResponse对象，用于向客户端返回响应
     * @param filterChain FilterChain对象，表示过滤器链，用于将请求传递给下一个过滤器
     * @throws ServletException 当Servlet遇到问题时抛出
     * @throws IOException 当发生输入/输出错误时抛出
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 自定义过滤逻辑，例如记录请求日志
        System.out.println("Request URI: " + request.getRequestURI());

        // 继续执行过滤链
        filterChain.doFilter(request, response);
    }
}
