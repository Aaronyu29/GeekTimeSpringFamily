package aaron.spring.data.waiterservice.service;

import aaron.spring.data.waiterservice.model.Coffee;
import aaron.spring.data.waiterservice.model.CoffeeOrder;
import aaron.spring.data.waiterservice.model.OrderState;
import aaron.spring.data.waiterservice.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Slf4j
@Transactional
@Service
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository orderRepository;

    public CoffeeOrder createOrder(String customer, Coffee...coffee) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer).items(Arrays.asList(coffee))
                .state(OrderState.INIT).build();
        CoffeeOrder saved = orderRepository.save(order);
        log.info("New Order : {}",saved);
        return saved;
    }

}
