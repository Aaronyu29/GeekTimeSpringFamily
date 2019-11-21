package aaron.spring.data.jedisdemo.service;

import aaron.spring.data.jedisdemo.model.Coffee;
import aaron.spring.data.jedisdemo.model.CoffeeOrder;
import aaron.spring.data.jedisdemo.model.OrderState;
import aaron.spring.data.jedisdemo.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class CofferOrderService {
    @Autowired
    private CoffeeOrderRepository orderRepository;

    public CoffeeOrder createOrder(Coffee... coffees) {
        CoffeeOrder saved = CoffeeOrder.builder().items(Arrays.asList(coffees)).state(OrderState.INIT).build();
        orderRepository.save(saved);
        log.info("Saved Order",saved);
        return saved;
    }

    public boolean updateOrder(CoffeeOrder coffeeOrder,OrderState state) {
        if(state.compareTo(coffeeOrder.getState()) <= 0) {
            log.warn("Wrong State Order: {},{}",coffeeOrder.getState(),state);
            return false;
        }

        coffeeOrder.setState(state);
        orderRepository.save(coffeeOrder);
        log.info("Update state: {}",coffeeOrder);
        return true;
    }
}
