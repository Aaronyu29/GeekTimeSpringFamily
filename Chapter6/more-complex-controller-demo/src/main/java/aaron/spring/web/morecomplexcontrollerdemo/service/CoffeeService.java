package aaron.spring.web.morecomplexcontrollerdemo.service;

import aaron.spring.web.morecomplexcontrollerdemo.model.Coffee;
import aaron.spring.web.morecomplexcontrollerdemo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = "CoffeeCache")
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public Coffee saveCoffee(String name, Money price) {
        return coffeeRepository.save(Coffee.builder().name(name).price(price).build());
    }

    @Cacheable
    public List<Coffee> findAllCoffee() {
        return coffeeRepository.findAll(Sort.by("id"));
    }

    public Coffee getOneCoffeeById(Long id) {
        return coffeeRepository.getOne(id);
    }

    public Coffee findCoffeeByName(String name) {
        return coffeeRepository.findByName(name);
    }

    public List<Coffee> findCoffeeAllByname(List<String> names) {
        return coffeeRepository.findByNameInOrderById(names);
    }
}
