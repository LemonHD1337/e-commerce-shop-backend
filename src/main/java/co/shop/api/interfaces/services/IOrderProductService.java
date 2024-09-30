package co.shop.api.interfaces.services;

import co.shop.api.dtos.orderProductDto.CreateOrderProductDto;
import co.shop.api.dtos.orderProductDto.OrderProductDto;
import co.shop.api.dtos.orderProductDto.UpdateOrderProductDto;

import java.util.List;

public interface IOrderProductService {
    List<OrderProductDto> getOrderProducts();
    OrderProductDto getOrderProductById(Long id);
    OrderProductDto createOrderProduct(CreateOrderProductDto createOrderProductDto);
    List<OrderProductDto> createManyOrderProducts(List<CreateOrderProductDto> createOrderProductsDto);
    OrderProductDto updateOrderProduct(Long id,UpdateOrderProductDto updateOrderProductDto);
    OrderProductDto deleteOrderProduct(Long id);
}
