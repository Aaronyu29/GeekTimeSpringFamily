package aaron.spring.web.simpleresttemplate;

import aaron.spring.web.simpleresttemplate.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@SpringBootApplication
@Slf4j
public class SimpleResttemplateDemoApplication implements ApplicationRunner {
    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {

        new SpringApplicationBuilder()
                .sources(SimpleResttemplateDemoApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/coffee/{id}")
                .build(1);
        ResponseEntity<Coffee> entity = restTemplate.getForEntity(uri, Coffee.class);
        log.info("Response Status {}, Response Headers: {}", entity.getStatusCode(), entity.getHeaders().toString());
        log.info("Coffee {}", entity.getBody());
        // 要开启 Chapter6 的 SpringBucks 服务。

        String coffeeUri = "http://localhost:8080/coffee/";
        Coffee coffeeRequest = Coffee.builder()
                .name("Americano").price(BigDecimal.valueOf(25.00))
                .build();
        Coffee coffee = restTemplate.postForObject(coffeeUri, coffeeRequest, Coffee.class);
        log.info("coffee {}", coffee);

        String allCoffee = restTemplate.getForObject(coffeeUri, String.class);
        log.info("allCoffee: {}", allCoffee);


    }
}
