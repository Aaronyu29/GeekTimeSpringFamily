package aaron.spring.springbucks.service;

import aaron.spring.springbucks.model.Coffee;
import aaron.spring.springbucks.model.CoffeeOrder;
import aaron.spring.springbucks.model.OrderState;
import aaron.spring.springbucks.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    /**
     * Optional<T> 是一种包装器对象，要么包装了类型 T 的对象，要么没有包装任何对象。
     *
     * @param name
     * @return
     */
    public Optional<Coffee> findOneCoffee(String name) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", exact().ignoreCase());
        Optional<Coffee> res = coffeeRepository.findOne(Example.of(Coffee.builder().name(name).build(), matcher));
        log.info("Coffee found {}", res);
        return res;
    }


}
