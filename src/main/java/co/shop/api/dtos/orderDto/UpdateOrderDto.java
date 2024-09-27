package co.shop.api.dtos.orderDto;

import co.shop.api.dtos.addressDto.AddressDto;
import co.shop.api.entities.enums.PaymentMethod;
import co.shop.api.entities.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderDto {
    private AddressDto address;

    @NotBlank(message = "Status nie może być pusty!")
    private Status status;

    @NotBlank(message = "Metoda płatności nie może być pusta!")
    private PaymentMethod paymentMethod;
}
