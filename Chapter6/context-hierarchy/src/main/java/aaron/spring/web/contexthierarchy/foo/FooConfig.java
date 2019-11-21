package aaron.spring.web.contexthierarchy.foo;

import aaron.spring.web.contexthierarchy.context.TestBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
@Configuration
/* Indicates that a class declares one or more {@link Bean @Bean} methods and
  may be processed by the Spring container to generate bean definitions and
  service requests for those beans at runtime, for example:*/
@EnableAspectJAutoProxy
public class FooConfig {
    @Bean
    public TestBean testBeanX() {
        return new TestBean("foo");
    }

    @Bean
    public TestBean testBeanY() {
        return new TestBean("foo");
    }

    @Bean
    public FooAspect fooAspect() {
        return new FooAspect();
    }

}
