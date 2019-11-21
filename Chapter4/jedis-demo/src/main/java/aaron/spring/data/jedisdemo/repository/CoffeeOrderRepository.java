package aaron.spring.data.jedisdemo.repository;

import aaron.spring.data.jedisdemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder,Long> {
}
