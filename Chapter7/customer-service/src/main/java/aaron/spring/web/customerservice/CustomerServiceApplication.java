package aaron.spring.web.customerservice;

import aaron.spring.web.customerservice.support.CustomerConnectionKeepAliveStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class CustomerServiceApplication {


    public static void main(String[] args) {

        new SpringApplicationBuilder(CustomerServiceApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(20);

        CloseableHttpClient client = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new CustomerConnectionKeepAliveStrategy())
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .disableAutomaticRetries()
                .build();

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(client);
        return httpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setReadTimeout(Duration.ofMillis(500))
                .setConnectTimeout(Duration.ofMillis(300))
                .requestFactory(this::httpRequestFactory)
                .build();
    }

}
