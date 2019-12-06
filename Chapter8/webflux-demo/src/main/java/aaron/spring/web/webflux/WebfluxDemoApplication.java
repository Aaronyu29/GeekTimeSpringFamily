package aaron.spring.web.webflux;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

@SpringBootApplication
public class WebfluxDemoApplication extends AbstractR2dbcConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxDemoApplication.class, args);
    }


    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(
                H2ConnectionConfiguration.builder().
                        inMemory("testdb").username("sa").build()
        );
    }
}
