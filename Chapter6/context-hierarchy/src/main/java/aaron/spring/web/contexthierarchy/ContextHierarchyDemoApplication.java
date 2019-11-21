package aaron.spring.web.contexthierarchy;

import aaron.spring.web.contexthierarchy.context.TestBean;
import aaron.spring.web.contexthierarchy.foo.FooConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@Slf4j
public class ContextHierarchyDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ContextHierarchyDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ApplicationContext fooContext = new AnnotationConfigApplicationContext(FooConfig.class);
        // fooContext -> parentContext
        ClassPathXmlApplicationContext soncontext = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"}, fooContext);

        TestBean bean = fooContext.getBean("testBeanX", TestBean.class);
        bean.hello();

        log.info("================");

        bean = soncontext.getBean("testBeanX",TestBean.class);
        bean.hello();

        // 在子上下文中寻找 bean: testBeanY,没有找到，委托父上下文找，命中，返回 testBeanY(hello)。
        bean = soncontext.getBean("testBeanY",TestBean.class);
        bean.hello();

        /**
         * 第一种情况：子上下文不开启切面，父上下文开启切面：
         * 结果：父上下文中 bean 被增强。子上下文中没被增强，不会打印 after 方法。
         * 第二种情况：子上下文开启切面，父上下文不开启切面。注解 FooConfig 中 FooAspect 方法。
         * 结果：只有 子上下文中方法被增强，打印 after 方法。
         * 结论：想对那一层的 bean 去做增强，我们配置 切面在那一层中。
         *
         * 通用的：所有 bean 都想要被增强。
         * 子上下文中保留 <aop:aspectj-autoproxy></aop:aspectj-autoproxy>，父上下文开启切面，子上下文去除切面。
         *  父和子上下文都开启 aop 增强，但是把 aspect 定义在 root 的 applicationContext 中。
         *
         *  子上下文定义了 aop 拦截，但是拦截目标在 父上下文中，增强会不会生效？第二种情况。
         */
    }
}
