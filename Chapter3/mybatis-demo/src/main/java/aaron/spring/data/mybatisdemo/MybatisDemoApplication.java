package aaron.spring.data.mybatisdemo;

import aaron.spring.data.mybatisdemo.mapper.CoffeeMapper;
import aaron.spring.data.mybatisdemo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("aaron.spring.data.mybatisdemo.mapper")
public class MybatisDemoApplication implements ApplicationRunner {
    @Autowired
    private CoffeeMapper coffeeMapper;

    // 报找不到 coffeeMapper，好像也可以实现。
    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee latte = Coffee.builder().name("latte").price(Money.of(CurrencyUnit.of("CNY"), 30.0)).build();
        int count = coffeeMapper.save(latte);
        log.info("Save {} Coffee {} ", count, latte);

        Coffee espresso = Coffee.builder().name("espresso").price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();
        count = coffeeMapper.save(espresso);
        log.info("Save2 {} coffee {}", count, espresso);

        Coffee coffee = coffeeMapper.findById(latte.getId());
        log.info("Find Coffee {} ", coffee);
    }
}
