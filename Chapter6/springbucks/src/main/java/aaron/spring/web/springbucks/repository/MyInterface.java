package aaron.spring.web.springbucks.repository;

import aaron.spring.web.springbucks.model.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface MyInterface extends CrudRepository<Coffee, Long> {
    // 接口不能实现接口。
}
