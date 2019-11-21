package aaron.spring.web.exceptiondemo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "T_ORDER")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoffeeOrder extends BaseEntity implements Serializable {

    private String customer;
    @JoinTable(name = "T_ORDER_COFFEE")
    @ManyToMany
    @OrderBy("id")
    private List<Coffee> items;
    @Enumerated
    @Column(nullable = false)
    private OrderState state;
}
