package aaron.spring.web.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Data
public class CoffeeOrder implements Serializable {
    private Long id;
    private String customer;
    private List<Coffee> items;
    private OrderState state;
    private Date createTime;
    private Date updateTime;
}
