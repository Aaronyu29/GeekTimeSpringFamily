package aaron.spring.data.cachedemo.repository;

import aaron.spring.data.cachedemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
