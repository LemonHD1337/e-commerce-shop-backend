package co.shop.api.services;

import co.shop.api.dtos.orderDto.CreateOrderDto;
import co.shop.api.dtos.orderDto.OrderDto;
import co.shop.api.dtos.orderDto.UpdateOrderDto;
import co.shop.api.exception.ResourceNotFoundException;
import co.shop.api.interfaces.mappers.IAddressMapper;
import co.shop.api.interfaces.mappers.IOrderMapper;
import co.shop.api.interfaces.services.IOrderService;
import co.shop.api.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository _orderRepository;
    private final IOrderMapper _orderMapper;
    private final IAddressMapper _addressMapper;

    public OrderService(OrderRepository orderRepository, IOrderMapper orderMapper, IAddressMapper addressMapper) {
        this._orderRepository = orderRepository;
        this._orderMapper = orderMapper;
        this._addressMapper = addressMapper;
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
        return _orderRepository
                .findById(id)
                .map(_orderMapper::toDto)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Order not found with id " + id)
                );
    }

    @Override
    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        var createdOrder = _orderRepository.save(_orderMapper.fromCreateOrderDtoToOrderEntity(createOrderDto));

        return _orderMapper.toDto(createdOrder);
    }

    @Override
    public OrderDto updateOrder(Long id, UpdateOrderDto updateOrderDto) {
        var updateOrder = _orderRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));

        updateOrder.setAddress(_addressMapper.toEntity(updateOrderDto.getAddress()));
        updateOrder.setStatus(updateOrderDto.getStatus());
        updateOrder.setPayment_method(updateOrderDto.getPaymentMethod());

        _orderRepository.save(updateOrder);

        return _orderMapper.toDto(updateOrder);
    }

    @Override
    public OrderDto deleteOrder(Long id) {
        var deleteOrder = _orderRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));

        _orderRepository.delete(deleteOrder);

        return _orderMapper.toDto(deleteOrder);
    }
}
