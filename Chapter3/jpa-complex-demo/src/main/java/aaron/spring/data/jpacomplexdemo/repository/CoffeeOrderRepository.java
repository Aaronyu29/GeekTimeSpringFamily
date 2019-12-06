package aaron.spring.data.jpacomplexdemo.repository;


import aaron.spring.data.jpacomplexdemo.model.CoffeeOrder;


import java.util.List;

public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {

    List<CoffeeOrder> findByCustomerOrderById(String customer);

    List<CoffeeOrder> findByItems_Name(String name);
}
