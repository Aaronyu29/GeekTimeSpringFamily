package aaron.spring.web.complexresttemplate;

import aaron.spring.web.complexresttemplate.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.util.List;

@SpringBootApplication
@Slf4j
public class ComplexResttemplateDemoApplication implements ApplicationRunner {
    @Autowired
    private RestTemplate restTemplate;
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public static void main(String[] args) {

        new SpringApplicationBuilder()
                .sources(ComplexResttemplateDemoApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/coffee/?name={name}")
                .build("mocha");
        RequestEntity<Void> requestEntity = RequestEntity.get(uri).accept(MediaType.APPLICATION_XML).build();
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
        // accept 指定客户端接受的内容类型。
        log.info("Response status: {}, Response Header: {}",response.getStatusCode(),response.getHeaders().toString());
        log.info("Response body: {}",response.getBody());

        String CoffeeUri = "http://localhost:8080/coffee/";
        Coffee request = Coffee.builder().name("Americano")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.00)).build();
        Coffee coffee = restTemplate.postForObject(CoffeeUri, request, Coffee.class);
        log.info("New Coffee: {}",coffee);

        ParameterizedTypeReference<List<Coffee>> reference = new ParameterizedTypeReference<List<Coffee>>(){};
        ResponseEntity<List<Coffee>> entity = restTemplate.exchange(CoffeeUri, HttpMethod.GET, null, reference);
        entity.getBody().forEach(c -> log.info("Coffee:{} ",c));

    }
}
