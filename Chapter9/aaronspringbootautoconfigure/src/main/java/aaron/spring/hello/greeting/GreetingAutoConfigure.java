package aaron.spring.hello.greeting;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(GreetingApplicationRunner.class)
public class GreetingAutoConfigure {
    @Bean
    @ConditionalOnMissingBean(GreetingApplicationRunner.class) // 上下文中没有这个 bean 时生效
    @ConditionalOnProperty(name = "greeting.enabled",havingValue = "true",matchIfMissing = true)
    public GreetingApplicationRunner greetingApplicationRunner(){
        return new GreetingApplicationRunner();
    }
}
