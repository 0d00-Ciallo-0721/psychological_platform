package sasu.platform.mhm.pojo.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果类，服务端响应的数据最终都会封装成此对象
 * @param <T> 响应数据的类型
 */
@Data
public class R<T> {

    private Integer code; //编码：200成功，其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map<String, Object> map = new HashMap<>(); //动态数据

    /**
     * 成功响应，带数据
     * @param object 响应数据
     * @return R对象
     */
    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 200;
        return r;
    }

    /**
     * 成功响应，带消息
     * @param msg 响应消息
     * @return R对象
     */
    public static <T> R<T> success(String msg) {
        R<T> r = new R<T>();
        r.data = null;
        r.code = 200;
        r.msg = msg;
        return r;
    }

    /**
     * 成功响应，带消息和数据
     * @param msg 响应消息
     * @param object 响应数据
     * @return R对象
     */
    public static <T> R<T> success(String msg, T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.msg = msg;
        r.code = 200;
        return r;
    }

    /**
     * 成功响应，无数据无消息
     * 常用于只需要返回状态码的场景
     * @return R对象
     */
    public static <T> R<T> success() {
        R<T> r = new R<T>();
        r.code = 200;
        return r;
    }

    /**
     * 失败响应，带错误消息
     * @param msg 错误消息
     * @return R对象
     */
    public static <T> R<T> error(String msg) {
        R<T> r = new R<T>();
        r.msg = msg;
        r.code = 500;
        return r;
    }

    /**
     * 失败响应，带错误消息和错误码
     * @param msg 错误消息
     * @param code 错误码
     * @return R对象
     */
    public static <T> R<T> error(String msg, Integer code) {
        R<T> r = new R<T>();
        r.code = code;
        r.msg = msg;
        return r;
    }

    /**
     * 添加动态数据
     * @param key 键
     * @param value 值
     * @return R对象，支持链式调用
     */
    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}

