package aaron.spring.web.customerservice;

import aaron.spring.web.customerservice.model.Coffee;
import aaron.spring.web.customerservice.model.CoffeeOrder;
import aaron.spring.web.customerservice.model.NewOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class CustomerRunner implements ApplicationRunner {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        readMenu();
        Long id = orderCoffee();
        queryOrder(id);
    }

    private void queryOrder(Long id) {
        CoffeeOrder order = restTemplate.getForObject("http://localhost:8080/order/{id}", CoffeeOrder.class, id);
        log.info("Query id: {}",order);
    }

    private Long orderCoffee() {
        NewOrderRequest orderRequest = NewOrderRequest.builder()
                .customer("Li Lei")
                .items(Arrays.asList("capuccino"))
                .build();
        RequestEntity<NewOrderRequest> request = RequestEntity
                .post(UriComponentsBuilder.fromUriString("http://localhost:8080/order/").build().toUri())
                .body(orderRequest);
        ResponseEntity<CoffeeOrder> responseEntity = restTemplate.exchange(request, CoffeeOrder.class);
        log.info("Order Request Status Code: {}",responseEntity.getStatusCode());
        Long id = responseEntity.getBody().getId();
        log.info("New id : {}",id);
        return id;
    }

    private void readMenu() {
        ParameterizedTypeReference<List<Coffee>> reference = new ParameterizedTypeReference<List<Coffee>>() {};
        ResponseEntity<List<Coffee>> entity =
                restTemplate.exchange("http://localhost:8080/coffee/", HttpMethod.GET, null, reference);
        entity.getBody().forEach(c -> log.info("Coffee List: {}",c));

    }
}
