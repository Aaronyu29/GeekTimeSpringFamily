package aaron.spring.web.jpademofaq.repository;

import aaron.spring.web.jpademofaq.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder,Long> {
}
