package aaron.spring.data.mongodemo;

import aaron.spring.data.mongodemo.converter.MoneyReadConverter;
import aaron.spring.data.mongodemo.model.Coffee;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Slf4j
public class MongoDemoApplication implements ApplicationRunner {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
    }


    public static void main(String[] args) {
        SpringApplication.run(MongoDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee coffee = Coffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .createTime(new Date())
                .updateTime(new Date()).build();
        mongoTemplate.save(coffee);
        log.info("Coffee saved {}", coffee);

        List<Coffee> list = mongoTemplate.find(
                Query.query(Criteria.where("name").is("espresso")), Coffee.class
        );
        log.info("Coffee list {}", list);
        list.forEach(c -> log.info("coffee {}", c));


        Thread.sleep(2000); // 为了看更新时间
        UpdateResult updateResult = mongoTemplate.updateFirst(Query.query(Criteria.where("name").is("espresso")),
                new Update().set("price", Money.ofMajor(CurrencyUnit.of("CNY"), 30)).currentDate("updateTime"),
                Coffee.class);
        log.info("Updated Result count: {}", updateResult.getModifiedCount());
        Coffee mongoTemplateById = mongoTemplate.findById(coffee.getId(), Coffee.class);
        log.info("Updated Result : {}", mongoTemplateById);

        mongoTemplate.remove(mongoTemplateById);
    }
}
