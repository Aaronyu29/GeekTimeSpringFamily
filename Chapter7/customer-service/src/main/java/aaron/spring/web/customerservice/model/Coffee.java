package aaron.spring.web.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.io.Serializable;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Coffee implements Serializable {
    private Long id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
