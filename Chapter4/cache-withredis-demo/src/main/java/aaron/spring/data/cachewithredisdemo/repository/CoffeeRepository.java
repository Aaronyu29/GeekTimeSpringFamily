package aaron.spring.data.cachewithredisdemo.repository;

import aaron.spring.data.cachewithredisdemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
