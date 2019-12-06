package aaron.spring.springbucks;

import aaron.spring.springbucks.model.Coffee;
import aaron.spring.springbucks.model.CoffeeOrder;
import aaron.spring.springbucks.model.OrderState;
import aaron.spring.springbucks.repository.CoffeeRepository;
import aaron.spring.springbucks.service.CoffeeOrderService;
import aaron.spring.springbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@Slf4j
@EnableJpaRepositories
@EnableTransactionManagement
public class SpringbucksApplication implements ApplicationRunner {
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService coffeeOrderService;
    @Autowired
    private CoffeeRepository coffeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbucksApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("All Coffee {} ", coffeeRepository.findAll());

        Optional<Coffee> latte = coffeeService.findOneCoffee("latte");
        if (latte.isPresent()) {
            CoffeeOrder order = coffeeOrderService.createOrder("Li lei", latte.get());
            log.info("Update init to paid {}", coffeeOrderService.updateState(order, OrderState.PAID));
            log.info("Update paid to init {}", coffeeOrderService.updateState(order, OrderState.INIT));
        }
    }
}
