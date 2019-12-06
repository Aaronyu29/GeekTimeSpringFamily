package aaron.spring.data.cachedemo.repository;

import aaron.spring.data.cachedemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
