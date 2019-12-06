package aaron.spring.springbucks.repository;

import aaron.spring.springbucks.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
