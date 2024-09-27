package co.shop.api.dtos.orderDto;

import co.shop.api.dtos.addressDto.AddressDto;
import co.shop.api.entities.enums.PaymentMethod;
import co.shop.api.entities.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderDto {
    private AddressDto address;
    private Status status;
    private PaymentMethod paymentMethod;
}
