package aaron.spring.web.exceptiondemo.repository;

import aaron.spring.web.exceptiondemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoffeeRepository extends JpaRepository<Coffee,Long> {
    List<Coffee> findByNameInOrderById(List<String> names);
    Coffee findByName(String name);
}
