package aaron.spring.boot.propertysourcedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主要作用：将自己内部配置的配置中心的 properties 文件和 Spring 的机制结合起来。
 */
@SpringBootApplication
@Slf4j
public class PropertySourceDemoApplication implements ApplicationRunner {
    @Value("${geektime.greeting}")
    private String greeting;

    public static void main(String[] args) {
        SpringApplication.run(PropertySourceDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("{}",greeting);
    }
}
