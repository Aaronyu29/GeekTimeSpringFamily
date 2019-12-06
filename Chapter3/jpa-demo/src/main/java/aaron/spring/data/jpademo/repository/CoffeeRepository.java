package aaron.spring.data.jpademo.repository;

import aaron.spring.data.jpademo.model.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {

}
