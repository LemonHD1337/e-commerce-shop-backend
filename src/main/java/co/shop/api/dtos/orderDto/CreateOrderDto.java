package co.shop.api.dtos.orderDto;

import co.shop.api.entities.enums.PaymentMethod;
import co.shop.api.entities.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderDto {
    private Long addressId;
    private Status status;
    private PaymentMethod paymentMethod;
}
