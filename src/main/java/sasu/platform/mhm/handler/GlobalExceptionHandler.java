package sasu.platform.mhm.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sasu.platform.mhm.pojo.common.R;

/**
 * 全局异常处理器
 * 统一处理应用程序中的异常，返回标准化的错误响应
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理认证异常（401未授权）
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<R<Object>> handleAuthenticationException(AuthenticationException e) {
        logger.warn("认证失败: {}", e.getMessage());
        R<Object> result = R.error("认证失败: " + e.getMessage(), 401);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    /**
     * 处理访问拒绝异常（403禁止访问）
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<R<Object>> handleAccessDeniedException(AccessDeniedException e) {
        logger.warn("访问被拒绝: {}", e.getMessage());
        R<Object> result = R.error("访问被拒绝: 权限不足", 403);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }

    /**
     * 处理凭证错误异常
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<R<Object>> handleBadCredentialsException(BadCredentialsException e) {
        logger.warn("凭证错误: {}", e.getMessage());
        R<Object> result = R.error("用户名或密码错误", 401);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    /**
     * 处理非法参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<R<Object>> handleIllegalArgumentException(IllegalArgumentException e) {
        logger.warn("参数错误: {}", e.getMessage());
        R<Object> result = R.error("参数错误: " + e.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    /**
     * 处理运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<R<Object>> handleRuntimeException(RuntimeException e) {
        logger.error("运行时异常: ", e);
        R<Object> result = R.error("服务器内部错误: " + e.getMessage(), 500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    /**
     * 处理所有其他异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<R<Object>> handleException(Exception e) {
        logger.error("未处理的异常: ", e);
        R<Object> result = R.error("服务器内部错误", 500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
}
