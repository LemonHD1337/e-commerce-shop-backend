package co.shop.api.interfaces.mappers;

import co.shop.api.dtos.orderDto.CreateOrderDto;
import co.shop.api.dtos.orderDto.OrderDto;
import co.shop.api.entities.Order;

public interface IOrderMapper {
    OrderDto toDto(Order order);
    Order fromCreateOrderDtoToOrderEntity(CreateOrderDto createOrderDto);
}
