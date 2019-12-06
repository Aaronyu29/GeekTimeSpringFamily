package aaron.spring.data.cachewithredisdemo;

import aaron.spring.data.cachewithredisdemo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching(proxyTargetClass = true)
@EnableJpaRepositories
@Slf4j
public class CacheWithredisDemoApplication implements ApplicationRunner {
    @Autowired
    private CoffeeService coffeeService;

    public static void main(String[] args) {
        SpringApplication.run(CacheWithredisDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Count size of {}", coffeeService.findAllCoffee().size());

        for (int i = 0; i < 5; i++) {
            log.info("Reading from cache.");
            coffeeService.findAllCoffee();
        }

        Thread.sleep(5_000);
        log.info("Reading after refresh.");
        coffeeService.findAllCoffee().forEach(c -> log.info("Coffee {}", c));
    }
}
