package co.shop.api.dtos.orderDto;

import co.shop.api.dtos.addressDto.AddressDto;
import co.shop.api.dtos.orderProductDto.OrderProductDto;
import co.shop.api.entities.enums.PaymentMethod;
import co.shop.api.entities.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class OrderDto {
    private Long id;
    private Set<OrderProductDto> products;
    private AddressDto address;
    private Status status;
    private PaymentMethod paymentMethod;
}
