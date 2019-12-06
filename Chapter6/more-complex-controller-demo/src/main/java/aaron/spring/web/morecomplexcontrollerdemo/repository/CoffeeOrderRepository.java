package aaron.spring.web.morecomplexcontrollerdemo.repository;

import aaron.spring.web.morecomplexcontrollerdemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
