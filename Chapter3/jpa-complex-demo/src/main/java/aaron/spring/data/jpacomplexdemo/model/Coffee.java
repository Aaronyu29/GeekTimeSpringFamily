package aaron.spring.data.jpacomplexdemo.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity // 对实体注释。任何 Hibernate 映射对象都要有这个注释。
@Table(name = "T_MENU_Coffee")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee extends BaseEntity implements Serializable {

    private String name;

    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money money;
}
