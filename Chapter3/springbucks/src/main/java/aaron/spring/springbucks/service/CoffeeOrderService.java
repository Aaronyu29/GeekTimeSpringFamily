package aaron.spring.springbucks.service;

import aaron.spring.springbucks.model.Coffee;
import aaron.spring.springbucks.model.CoffeeOrder;
import aaron.spring.springbucks.model.OrderState;
import aaron.spring.springbucks.repository.CoffeeOrderRepository;
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

    /**
     * Java 中开始的语法，接受 0 或多个 coffee 参数。
     * @param customer
     * @param coffee
     * @return
     */
    public CoffeeOrder createOrder(String customer, Coffee... coffee){
        CoffeeOrder order = CoffeeOrder.builder().customer(customer).items(Arrays.asList(coffee)).state(OrderState.INIT).build();
        CoffeeOrder saved = orderRepository.save(order);
        log.info("New CoffeeOrder {}",saved);
        return saved;
    }
    public boolean updateState(CoffeeOrder order, OrderState state) {
        if(state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong State Order: {}, {}",state,order.getState());
            return false;
        }
        order.setState(state);
        orderRepository.save(order);
        return true;
    }




}
