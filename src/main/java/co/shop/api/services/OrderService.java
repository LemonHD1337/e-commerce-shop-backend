package co.shop.api.services;

import co.shop.api.dtos.orderDto.CreateOrderDto;
import co.shop.api.dtos.orderDto.OrderDto;
import co.shop.api.dtos.orderDto.UpdateOrderDto;
import co.shop.api.entities.Order;
import co.shop.api.exception.ResourceNotFoundException;
import co.shop.api.interfaces.mappers.IOrderMapper;
import co.shop.api.interfaces.services.IOrderService;
import co.shop.api.repositories.OrderRepository;
import co.shop.api.validation.CentralValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository _orderRepository;
    private final IOrderMapper _orderMapper;
    private final CentralValidator _validator;

    public OrderService(
            OrderRepository orderRepository,
            IOrderMapper orderMapper,
            CentralValidator validator
    ) {
        this._orderRepository = orderRepository;
        this._orderMapper = orderMapper;
        this._validator = validator;
    }

    @Override
    public List<OrderDto> getOrders() {
        return _orderRepository
                .findAll()
                .stream()
                .map(_orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return _orderMapper.toDto(getOrderEntityById(id));
    }

    @Override
    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        validate(createOrderDto);
        var createdOrder = _orderRepository.save(_orderMapper.fromCreateOrderDtoToOrderEntity(createOrderDto));
        return _orderMapper.toDto(createdOrder);
    }

    @Override
    public OrderDto updateOrder(Long id, UpdateOrderDto updateOrderDto) {
        validate(updateOrderDto);
        var updateOrder = getOrderEntityById(id);
        var updatedOrder = _orderMapper.formUpdateOrderDtoToOrderEntity(updateOrder, updateOrderDto);
        return _orderMapper.toDto(_orderRepository.save(updatedOrder));
    }

    @Override
    public OrderDto deleteOrder(Long id) {
        var deleteOrder = getOrderEntityById(id);
        _orderRepository.delete(deleteOrder);
        return _orderMapper.toDto(deleteOrder);
    }

    private Order getOrderEntityById(Long id){
        return _orderRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Order not found with id " + id)
                );
    }

    private void validate(Object obj){
        _validator.validate(obj);
    }
}
