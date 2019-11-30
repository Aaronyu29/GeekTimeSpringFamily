package aaron.spring.web.springbucks.service;

import aaron.spring.web.springbucks.model.Coffee;
import aaron.spring.web.springbucks.model.CoffeeOrder;
import aaron.spring.web.springbucks.model.OrderState;
import aaron.spring.web.springbucks.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
@Slf4j
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository orderRepository;

    public CoffeeOrder get(Long id) {
        return orderRepository.getOne(id);
    }

    public CoffeeOrder createOrder(String customer, Coffee...coffees){
        CoffeeOrder coffeeOrder = CoffeeOrder.builder()
                .customer(customer)
                .items(Arrays.asList(coffees))
                .state(OrderState.INIT)
                .build();
        CoffeeOrder saved = orderRepository.save(coffeeOrder);
        log.info("New Order : {}",saved);
        return saved;
    }
}
