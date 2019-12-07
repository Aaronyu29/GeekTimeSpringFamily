package aaron.spring.hello.greeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
@Slf4j
public class GreetingApplicationRunner implements ApplicationRunner {
    public void run(ApplicationArguments args) throws Exception {
      log.info("We all like Spring!");
    }

    private String name;
    public GreetingApplicationRunner() {
        this("Geektime");
    }

    public GreetingApplicationRunner(String name) {
        this.name = name;
        log.info("Initializing the GreetingApplicationRunner: {}",name);
    }

}
