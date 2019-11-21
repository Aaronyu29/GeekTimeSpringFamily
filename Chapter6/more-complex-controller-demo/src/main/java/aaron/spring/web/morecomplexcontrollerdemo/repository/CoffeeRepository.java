package aaron.spring.web.morecomplexcontrollerdemo.repository;

import aaron.spring.web.morecomplexcontrollerdemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoffeeRepository extends JpaRepository<Coffee,Long> {
    List<Coffee> findByNameInOrderById(List<String> name);
    Coffee findByName(String name);
}
