package aaron.spring.data.waiterservice.controller;

import aaron.spring.data.waiterservice.model.Coffee;
import aaron.spring.data.waiterservice.model.CoffeeOrder;
import aaron.spring.data.waiterservice.service.CoffeeOrderService;
import aaron.spring.data.waiterservice.service.CoffeeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService orderService;

    @PostMapping("/createOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new Order {}",newOrder);
        Coffee[] coffeeList = coffeeService.getCoffeeByName(newOrder.getItems())
                .toArray(new Coffee[]{});
        return orderService.createOrder(newOrder.getCustomer(),coffeeList);
    }
}
