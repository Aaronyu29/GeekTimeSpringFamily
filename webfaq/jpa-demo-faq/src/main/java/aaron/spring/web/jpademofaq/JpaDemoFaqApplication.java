package aaron.spring.web.jpademofaq;

import aaron.spring.web.jpademofaq.model.Coffee;
import aaron.spring.web.jpademofaq.model.CoffeeOrder;
import aaron.spring.web.jpademofaq.repository.CoffeeOrderRepository;
import aaron.spring.web.jpademofaq.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@Slf4j
@EnableJpaRepositories
public class JpaDemoFaqApplication implements CommandLineRunner {
    @Autowired
    private CoffeeRepository coffeeReposity;
    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoFaqApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        initorders();
    }

    private void initorders() {
        Coffee espresso = Coffee.builder().name("espresso")
                .money(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();

        coffeeReposity.save(espresso);
        log.info("Coffee1 {} " + espresso);
        Coffee latte = Coffee.builder().name("lattee")
                .money(Money.of(CurrencyUnit.of("CNY"), 30.0)).build();
        coffeeReposity.save(latte);
        log.info("Coffee2 {} " + latte);

        CoffeeOrder order = CoffeeOrder.builder().customer("Li lei").state(0)
                .items(Collections.singletonList(espresso))
                .build();
        coffeeOrderRepository.save(order);
        log.info("CoffeeOrder1 {} " + order);

        order = CoffeeOrder.builder().customer("Li lei").state(0).items(Arrays.asList(espresso, latte)).build();
        coffeeOrderRepository.save(order);
        log.info("CoffeeOrder2 {} " + order);

    }
}
