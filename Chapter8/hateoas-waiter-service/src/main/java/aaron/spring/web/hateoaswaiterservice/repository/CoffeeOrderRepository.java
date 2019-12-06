package aaron.spring.web.hateoaswaiterservice.repository;

import aaron.spring.web.hateoaswaiterservice.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
