package aaron.spring.data.cachewithredisdemo.service;

import aaron.spring.data.cachewithredisdemo.model.Coffee;
import aaron.spring.data.cachewithredisdemo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@CacheConfig(cacheNames = "coffee")
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Cacheable
    public List<Coffee> findAllCoffee() {

        return coffeeRepository.findAll();
    }

    @CacheEvict
    public void reloadCoffee() {
        log.info("reload cache");
    }

}
