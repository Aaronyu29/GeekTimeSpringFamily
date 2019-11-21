package aaron.spring.data.cachedemo;

import aaron.spring.data.cachedemo.service.CoffeeService;
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
@Slf4j
@EnableJpaRepositories
@EnableTransactionManagement
@EnableCaching(proxyTargetClass = true)
public class CacheDemoApplication  implements ApplicationRunner {
	@Autowired
	private CoffeeService coffeeService;

	public static void main(String[] args) {
		SpringApplication.run(CacheDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Count of Order {}",coffeeService.findAllCoffee().size());

		for(int i = 0; i<10; i++) {
			log.info("Reading from cache.");
			coffeeService.findAllCoffee();
		}
		coffeeService.reloadCoffee();
		log.info("refreshing the cache.");
		coffeeService.findAllCoffee().forEach(c -> log.info("Coffee {}",c));
	}
}
