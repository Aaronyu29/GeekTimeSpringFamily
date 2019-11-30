package aaron.spring.web.customerservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Builder
public class NewOrderRequest {
    private String customer;
    private List<String> items;
}
