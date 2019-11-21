package aaron.spring.data.redisdemo.repository;

import aaron.spring.data.redisdemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee,Long> {
}
