package sasu.platform.mhm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("sasu.platform.mhm.mapper")
public class MetalHealthPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetalHealthPlatformApplication.class, args);
    }

}
