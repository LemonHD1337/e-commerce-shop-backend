package co.shop.api.dtos.orderProductDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderProductDto {

    @NotBlank(message = "Id produktu nie może być pusty!")
    private Long productId;

    @NotBlank(message = "Id zamowienia nie może być pusty!")
    private Long orderId;

    @NotBlank(message = "Ilość zamówionego produktu nie może być pusta")
    private int quantity;
}
