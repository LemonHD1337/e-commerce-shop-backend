package co.shop.api.dtos.orderProductDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderProductDto {
    private Long productId;
    private Long orderId;
    private int quantity;
}
