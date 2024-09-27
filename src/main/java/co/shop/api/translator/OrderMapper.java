package co.shop.api.translator;

import co.shop.api.dtos.orderDto.CreateOrderDto;
import co.shop.api.dtos.orderDto.OrderDto;
import co.shop.api.entities.Order;
import co.shop.api.interfaces.mappers.IAddressMapper;
import co.shop.api.interfaces.mappers.IOrderMapper;
import co.shop.api.interfaces.mappers.IOrderProductMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper implements IOrderMapper {

    private final IAddressMapper _addressMapper;
    private final IOrderProductMapper _orderProductMapper;

    public OrderMapper(IAddressMapper addressMapper, IOrderProductMapper orderProductMapper) {
        this._addressMapper = addressMapper;
        this._orderProductMapper = orderProductMapper;
    }

    @Override
    public OrderDto toDto(Order order) {
        var orderDto = new OrderDto();

        var productsDto = order.getOrderProducts().stream().map(_orderProductMapper::toDto).collect(Collectors.toSet());

        orderDto.setId(order.getId());
        orderDto.setAddress(_addressMapper.toDto(order.getAddress()));
        orderDto.setStatus(order.getStatus());
        orderDto.setPaymentMethod(order.getPayment_method());
        orderDto.setProducts(productsDto);

        return orderDto;
    }

    @Override
    public Order fromCreateOrderDtoToOrderEntity(CreateOrderDto createOrderDto) {
        var order = new Order();

        order.setAddress(_addressMapper.toEntity(createOrderDto.getAddress()));
        order.setStatus(createOrderDto.getStatus());
        order.setPayment_method(createOrderDto.getPaymentMethod());

        return order;
    }
}
