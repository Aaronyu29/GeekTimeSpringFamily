package aaron.spring.web.exceptiondemo.service;

import aaron.spring.web.exceptiondemo.model.Coffee;
import aaron.spring.web.exceptiondemo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
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
        return coffeeRepository.save(Coffee.builder()
        .name(name).price(price).build());
    }

    public Coffee getById(Long id) {
        return coffeeRepository.getOne(id);
    }

    public List<Coffee> getAll() {
        return coffeeRepository.findAll(Sort.by("id"));
    }

    public List<Coffee> getAllCoffeeByNames(List<String> names) {
        return coffeeRepository.findByNameInOrderById(names);
    }

    public Coffee getCoffeeByName(String name) {
        return coffeeRepository.findByName(name);
    }

}
