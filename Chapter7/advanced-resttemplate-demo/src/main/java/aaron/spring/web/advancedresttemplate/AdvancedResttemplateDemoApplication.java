package aaron.spring.web.advancedresttemplate;

import aaron.spring.web.advancedresttemplate.support.CustomerConnectionKeepAliveStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * HTTP 连接管理的一些设置。
 */
@SpringBootApplication
@Slf4j
public class AdvancedResttemplateDemoApplication {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofMillis(100))
                .setReadTimeout(Duration.ofMillis(500))
                .requestFactory(this::requestFactory)
                .build();
        // this::instanceMethod
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory requestFactory() {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
        manager.setMaxTotal(200);
        manager.setDefaultMaxPerRoute(20);

        CloseableHttpClient client = HttpClients.custom()
                .setConnectionManager(manager)
                .disableAutomaticRetries()
                .evictIdleConnections(30, TimeUnit.SECONDS)

                // 有 Keep-Alive 认里面的值，没有的话永久有效
                //.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                // 换成自定义的
                .setKeepAliveStrategy(new CustomerConnectionKeepAliveStrategy())
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
        return factory;
    }

    public static void main(String[] args) {
        SpringApplication.run(AdvancedResttemplateDemoApplication.class, args);
    }

}
