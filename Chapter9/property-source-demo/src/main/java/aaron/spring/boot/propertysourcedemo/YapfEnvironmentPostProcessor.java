package aaron.spring.boot.propertysourcedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Slf4j
public class YapfEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private PropertiesPropertySourceLoader sourceLoader = new PropertiesPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        ClassPathResource classPathResource = new ClassPathResource("yapf.properties");
        try {
            PropertySource<?> ps = sourceLoader.load("YetAnotherPropertiesFile", classPathResource)
                    .get(0);
            propertySources.addFirst(ps);
        } catch (IOException e) {
            log.error("Exception: ",e);
        }

    }
}
