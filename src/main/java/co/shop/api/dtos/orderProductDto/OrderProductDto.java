package co.shop.api.dtos.orderProductDto;

import co.shop.api.dtos.productDto.ProductDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductDto {
    private Long id;
    private ProductDto product;
    private int quantity;
}
