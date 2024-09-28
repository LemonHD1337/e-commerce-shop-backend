package co.shop.api.interfaces.mappers;

import co.shop.api.dtos.orderProductDto.CreateOrderProductDto;
import co.shop.api.dtos.orderProductDto.OrderProductDto;
import co.shop.api.dtos.orderProductDto.UpdateOrderProductDto;
import co.shop.api.entities.OrderProduct;

public interface IOrderProductMapper {
    OrderProductDto toDto(OrderProduct orderProduct);
    OrderProduct fromCreateOrderProductDtoToEntity(CreateOrderProductDto createOrderProductDto);
    OrderProduct fromUpdateOrderProductDtoToEntity(OrderProduct orderProductFromDb,UpdateOrderProductDto updateOrderProductDto);
}
