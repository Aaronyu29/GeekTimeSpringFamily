package aaron.spring.data.jedisdemo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "T_ORDER")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class CoffeeOrder extends BaseEntity implements Serializable {

    private String customer;
    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;
    @Enumerated
    @Column(nullable = false)
    private OrderState state;
}
