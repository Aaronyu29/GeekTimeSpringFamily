package aaron.spring.web.complexcontroller.service;

import aaron.spring.web.complexcontroller.model.Coffee;
import aaron.spring.web.complexcontroller.model.CoffeeOrder;
import aaron.spring.web.complexcontroller.model.OrderState;
import aaron.spring.web.complexcontroller.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Transactional
@Slf4j
@Service
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository orderRepository;

    public CoffeeOrder getOneOrder(Long id) {
        return orderRepository.getOne(id);
    }

    public CoffeeOrder createOrder(String customer, Coffee...coffees) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer).state(OrderState.INIT)
                .items(Arrays.asList(coffees)).build();
        log.info("New Order {}",order);
        orderRepository.save(order);
        return order;
    }
}
