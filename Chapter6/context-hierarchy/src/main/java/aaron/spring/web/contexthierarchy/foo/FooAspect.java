package aaron.spring.web.contexthierarchy.foo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

// 声明切面
@Aspect
@Slf4j
public class FooAspect {
    @AfterReturning("bean(testBean*)")
    public void printAfter() {
        log.info("After print hello");
    }
}
