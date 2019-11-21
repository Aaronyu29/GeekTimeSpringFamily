package aaron.spring.web.jsonview.repository;

import aaron.spring.web.jsonview.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder,Long> {
}
