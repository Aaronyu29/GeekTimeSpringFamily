package aaron.spring.data.jpademo.repository;

import aaron.spring.data.jpademo.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder,Long> {
}
