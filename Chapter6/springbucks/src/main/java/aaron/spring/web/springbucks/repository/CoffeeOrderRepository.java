package aaron.spring.web.springbucks.repository;

import aaron.spring.web.springbucks.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {
}
