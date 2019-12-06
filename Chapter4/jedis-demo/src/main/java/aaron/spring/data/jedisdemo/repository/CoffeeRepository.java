package aaron.spring.data.jedisdemo.repository;

import aaron.spring.data.jedisdemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
