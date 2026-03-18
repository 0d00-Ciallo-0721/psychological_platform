package sasu.platform.mhm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sasu.platform.mhm.mapper.UserMapper;

@SpringBootTest
class MetalHealthPlatformApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(userMapper.selectByUsername("student1"));
    }
    @Test
    void testBCrypt(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
    @Test
    void testThread(){
        // 伪代码示例
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        // 线程 A
        threadLocal.set("用户A");
        String value = threadLocal.get(); // 拿到 "用户A"
        System.out.println( value);
        // 线程 B（同时运行）
        threadLocal.set("用户B");
        String value1 = threadLocal.get(); // 拿到 "用户B"，不会拿到 "用户A"
        value = threadLocal.get(); // 拿到 "用户A"
        System.out.println( value);
        System.out.println( value1);
    }

}
