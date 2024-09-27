package co.shop.api.interfaces.services;

import co.shop.api.dtos.orderDto.CreateOrderDto;
import co.shop.api.dtos.orderDto.OrderDto;
import co.shop.api.dtos.orderDto.UpdateOrderDto;

import java.util.List;

public interface IOrderService {
    List<OrderDto> getOrders();
    OrderDto getOrderById(Long id);
    OrderDto createOrder(CreateOrderDto createOrderDto);
    OrderDto updateOrder(Long id, UpdateOrderDto updateOrderDto);
    OrderDto deleteOrder(Long id);
}
