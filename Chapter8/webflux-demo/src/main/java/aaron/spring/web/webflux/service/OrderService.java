package aaron.spring.web.webflux.service;

import aaron.spring.web.webflux.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private CoffeeRepository coffeeRepository;


}
