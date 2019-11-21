package aaron.spring.data.redisdemo.service;

import aaron.spring.data.redisdemo.model.Coffee;
import aaron.spring.data.redisdemo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CoffeeService {
    private static final String CACHE = "springbucks-coffee";
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private RedisTemplate<String, Coffee> redisTemplate;

    public List<Coffee> findAllCoffee() {
        return coffeeRepository.findAll();
    }

    public Optional<Coffee> findOneCoffee(String name) {
        HashOperations<String,String,Coffee> hashOperations = redisTemplate.opsForHash();
        if(redisTemplate.hasKey(CACHE) && hashOperations.hasKey(CACHE,name)) {
            log.info("get coffee from {}",name);
            return Optional.of(hashOperations.get(CACHE,name));
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.findOne(Example.of(Coffee.builder().name(name).build(), matcher));
        log.info("Found Coffee {} ",coffee);
        if(coffee.isPresent()) {
            log.info("Put Coffee {} to redis",name);
            hashOperations.put(CACHE,name,coffee.get());
            redisTemplate.expire(CACHE,1, TimeUnit.MINUTES);
        }
        return coffee;
    }

}
