package co.shop.api.services;

import co.shop.api.dtos.orderProductDto.CreateOrderProductDto;
import co.shop.api.dtos.orderProductDto.OrderProductDto;
import co.shop.api.dtos.orderProductDto.UpdateOrderProductDto;
import co.shop.api.exception.ResourceNotFoundException;
import co.shop.api.interfaces.mappers.IOrderProductMapper;
import co.shop.api.interfaces.services.IOrderProductService;
import co.shop.api.repositories.OrderProductRepository;
import co.shop.api.repositories.OrderRepository;
import co.shop.api.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService implements IOrderProductService {

    private final OrderProductRepository _orderProductRepository;
    private final IOrderProductMapper _orderProductMapper;
    private final ProductRepository _productRepository;
    private final OrderRepository _orderRepository;

    public OrderProductService(OrderProductRepository orderProductRepository, IOrderProductMapper orderProductMapper, ProductRepository productRepository, OrderRepository orderRepository) {
        this._orderProductRepository = orderProductRepository;
        this._orderProductMapper = orderProductMapper;
        this._productRepository = productRepository;
        this._orderRepository = orderRepository;
    }

    @Override
    public List<OrderProductDto> getOrderProducts() {
        return _orderProductRepository.findAll().stream().map(_orderProductMapper::toDto).toList();
    }

    @Override
    public OrderProductDto getOrderProductById(Long id) {
        return _orderProductRepository
                .findById(id)
                .map(_orderProductMapper::toDto)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Order product not found with id: " + id)
                );
    }

    @Override
    public OrderProductDto createOrderProduct(CreateOrderProductDto createOrderProductDto) {
        var productEntity = _productRepository.findById(createOrderProductDto.getProductId()).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id: " + createOrderProductDto.getProductId())
        );

        var orderEntity = _orderRepository.findById(createOrderProductDto.getOrderId()).orElseThrow(
                () -> new ResourceNotFoundException("Order not found with id: " + createOrderProductDto.getOrderId())
        );

        var orderProductEntity = _orderProductMapper.fromCreateOrderProductDtoToEntity(createOrderProductDto);

        orderProductEntity.setOrder(orderEntity);
        orderProductEntity.setProduct(productEntity);

        _orderProductRepository.save(orderProductEntity);

        return _orderProductMapper.toDto(orderProductEntity);
    }

    @Override
    public OrderProductDto updateOrderProduct(Long id, UpdateOrderProductDto updateOrderProductDto) {
        var orderProductEntity = _orderProductRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order product not found with id: " + id)
        );

        var orderEntity = _orderRepository.findById(updateOrderProductDto.getOrderId()).orElseThrow(
                () -> new ResourceNotFoundException("Order not found with id: " + updateOrderProductDto.getOrderId())
        );

        var productEntity = _productRepository.findById(updateOrderProductDto.getProductId()).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id: " + updateOrderProductDto.getProductId())
        );

        orderProductEntity.setQuantity(updateOrderProductDto.getQuantity());
        orderProductEntity.setProduct(productEntity);
        orderProductEntity.setOrder(orderEntity);

        _orderProductRepository.save(orderProductEntity);

        return _orderProductMapper.toDto(orderProductEntity);
    }

    @Override
    public OrderProductDto deleteOrderProduct(Long id) {
        var deleteOrderProductEntity = _orderProductRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order product not found with id: " + id)
        );

        _orderProductRepository.delete(deleteOrderProductEntity);

        return _orderProductMapper.toDto(deleteOrderProductEntity);
    }
}
