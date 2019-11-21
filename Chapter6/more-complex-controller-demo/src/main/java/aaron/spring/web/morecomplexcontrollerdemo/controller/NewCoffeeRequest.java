package aaron.spring.web.morecomplexcontrollerdemo.controller;

import lombok.Data;
import org.joda.money.Money;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class NewCoffeeRequest {
    @NotEmpty
    private String name;
    @NotNull
    private Money price;
}
