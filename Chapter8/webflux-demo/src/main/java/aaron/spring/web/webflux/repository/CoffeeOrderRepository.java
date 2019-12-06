package aaron.spring.web.webflux.repository;

import aaron.spring.web.webflux.model.Coffee;
import aaron.spring.web.webflux.model.CoffeeOrder;
import aaron.spring.web.webflux.model.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;

@Repository
public class CoffeeOrderRepository {
    @Autowired
    private DatabaseClient databaseClient;

    public Mono<CoffeeOrder> get(Long id) {
        return databaseClient.execute()
                .sql("select * from t_order where id =" + id)
                .map((r, rm) ->
                        CoffeeOrder.builder().id(id)
                                .customer(r.get("customer", String.class))
                                .state(OrderState.values()[r.get("state", Integer.class)])
                                .createTime(r.get("create_time", Date.class))
                                .updateTime(r.get("update_time", Date.class))
                                .items(new ArrayList<Coffee>())
                                .build()
                )
                .first()
                .flatMap(o ->
                        databaseClient.execute()
                                .sql("select c.* from t_order c,t_order_coffee oc" +
                                        "where c.id = oc.items_id and oc.coffee_order_id =" + id)
                                .as(Coffee.class)
                                .fetch()
                                .all()
                                .collectList()
                                .flatMap(l -> {
                                    o.getItems().addAll(l);
                                    return Mono.just(o);
                                })
                );
    }


}
