package aaron.spring.boot.autoconfiguredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("aaron.spring.hello.greeting")
public class AutoconfigureDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(AutoconfigureDemoApplication.class, args);
    }

   /* @Bean
    public GreetingApplicationRunner greetingApplicationRunner() {
        return new GreetingApplicationRunner("Money");
    }*/
}
