package aaron.spring.data.cachedemo.service;

import aaron.spring.data.cachedemo.model.Coffee;
import aaron.spring.data.cachedemo.model.CoffeeOrder;
import aaron.spring.data.cachedemo.model.OrderState;
import aaron.spring.data.cachedemo.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Slf4j
@Service
@Transactional
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository orderRepository;

    public CoffeeOrder createOrder(String customer, Coffee...coffees) {
        CoffeeOrder order = CoffeeOrder.builder().customer(customer)
                .items(Arrays.asList(coffees)).state(OrderState.INIT).build();
        orderRepository.save(order);
        log.info("New order: {}",order);
        return order;
    }

    public boolean update(CoffeeOrder order,OrderState state) {
        if(state.compareTo(order.getState()) <= 0) {
            log.warn("It's not allowed to change the {}, to {}",order.getState(),state);
            return false;
        }
        order.setState(state);
        log.info("New Order {}",order);
        return true;
    }
}
