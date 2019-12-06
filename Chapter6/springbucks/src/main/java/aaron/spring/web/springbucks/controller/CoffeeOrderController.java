package aaron.spring.web.springbucks.controller;

import aaron.spring.web.springbucks.controller.request.NewOrderRequest;
import aaron.spring.web.springbucks.model.Coffee;
import aaron.spring.web.springbucks.model.CoffeeOrder;
import aaron.spring.web.springbucks.service.CoffeeOrderService;
import aaron.spring.web.springbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService orderService;

    @GetMapping("/{id}")
    public CoffeeOrder getOrder(@PathVariable("id") Long id) {
        CoffeeOrder coffeeOrder = orderService.get(id);
        log.info("Get Order: {}", coffeeOrder);
        return coffeeOrder;
    }


    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody NewOrderRequest orderRequest) {
        log.info("Receive new Order: {}", orderRequest);
        Coffee[] coffees = coffeeService.getAllCoffeeByNames(orderRequest.getItems())
                .toArray(new Coffee[]{});
        return orderService.createOrder(orderRequest.getCustomer(), coffees);
    }
}
