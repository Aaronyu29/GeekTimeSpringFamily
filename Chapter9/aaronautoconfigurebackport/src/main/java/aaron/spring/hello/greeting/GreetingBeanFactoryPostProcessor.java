package aaron.spring.hello.greeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.util.ClassUtils;

@Slf4j
public class GreetingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        boolean isPresent = ClassUtils.isPresent("aaron.spring.hello.greeting.GreetingApplicationRunner",
                GreetingBeanFactoryPostProcessor.class.getClassLoader());
        if(!isPresent) {
            log.info("GreetingApplicationRunner is not in the classPath");
            return;
        }

        if(beanFactory.containsBeanDefinition("GreetingApplicationRunner")) {
            log.info("We already have a greetingApplicationRunner bean registered.");
            return;
        }

        register(beanFactory);


    }

    private void register(ConfigurableListableBeanFactory beanFactory) {
        if(beanFactory instanceof BeanDefinitionRegistry) {
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(GreetingApplicationRunner.class);
            ((BeanDefinitionRegistry) beanFactory).registerBeanDefinition("GreetingApplicationRunner"
                    ,beanDefinition);
        } else {
            beanFactory.registerSingleton("GreetingApplicationRunner",
                    new GreetingApplicationRunner());
        }
    }
}
