package aaron.spring.web.exceptiondemo.repository;

import aaron.spring.web.exceptiondemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
