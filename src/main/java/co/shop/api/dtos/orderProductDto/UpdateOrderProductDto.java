package co.shop.api.dtos.orderProductDto;

import jakarta.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderProductDto {

    private Long productId;

    private Long orderId;

    @Min(value = 1, message = "Zamowiony produkt musi być w nakładzie chociaż jednej sztuki")
    private int quantity;
}
