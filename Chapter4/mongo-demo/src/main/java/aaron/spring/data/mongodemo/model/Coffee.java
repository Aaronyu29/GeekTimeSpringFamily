package aaron.spring.data.mongodemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * Mongo 里面的 id 是使用的 @annotation.id
 * 没有  javax.persistence 这个包
 */
public class Coffee {
    @Id
    private String id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
