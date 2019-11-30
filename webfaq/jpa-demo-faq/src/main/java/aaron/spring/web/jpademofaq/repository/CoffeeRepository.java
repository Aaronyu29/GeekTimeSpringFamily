package aaron.spring.web.jpademofaq.repository;

import aaron.spring.web.jpademofaq.model.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee,Long> {

}
