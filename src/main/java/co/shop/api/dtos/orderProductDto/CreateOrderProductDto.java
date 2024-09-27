package co.shop.api.dtos.orderProductDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderProductDto {
    private Long productId;
    private Long orderId;
    private int quantity;
}
