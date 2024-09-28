package co.shop.api.services;

import co.shop.api.dtos.orderProductDto.CreateOrderProductDto;
import co.shop.api.dtos.orderProductDto.OrderProductDto;
import co.shop.api.dtos.orderProductDto.UpdateOrderProductDto;
import co.shop.api.entities.OrderProduct;
import co.shop.api.exception.ResourceNotFoundException;
import co.shop.api.interfaces.mappers.IOrderProductMapper;
import co.shop.api.interfaces.services.IOrderProductService;
import co.shop.api.repositories.OrderProductRepository;
import co.shop.api.validation.CentralValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService implements IOrderProductService {

    private final OrderProductRepository _orderProductRepository;
    private final IOrderProductMapper _orderProductMapper;
    private final CentralValidator _validator;

    public OrderProductService(
            OrderProductRepository orderProductRepository,
            IOrderProductMapper orderProductMapper,
            CentralValidator validator
    ) {
        this._orderProductRepository = orderProductRepository;
        this._orderProductMapper = orderProductMapper;
        this._validator = validator;
    }

    @Override
    public List<OrderProductDto> getOrderProducts() {
        return _orderProductRepository.findAll().stream().map(_orderProductMapper::toDto).toList();
    }

    @Override
    public OrderProductDto getOrderProductById(Long id) {
        return _orderProductMapper.toDto(getOrderProductEntityById(id));
    }

    @Override
    public OrderProductDto createOrderProduct(CreateOrderProductDto createOrderProductDto) {
        validate(createOrderProductDto);
        var orderProduct = _orderProductMapper.fromCreateOrderProductDtoToEntity(createOrderProductDto);
        return _orderProductMapper.toDto(_orderProductRepository.save(orderProduct));
    }

    @Override
    public OrderProductDto updateOrderProduct(Long id, UpdateOrderProductDto updateOrderProductDto) {
        validate(updateOrderProductDto);
        var orderProductEntity = getOrderProductEntityById(id);
        var changedOrderProduct = _orderProductMapper.fromUpdateOrderProductDtoToEntity(orderProductEntity, updateOrderProductDto);
        return _orderProductMapper.toDto(_orderProductRepository.save(changedOrderProduct));
    }

    @Override
    public OrderProductDto deleteOrderProduct(Long id) {
        var deleteOrderProductEntity = getOrderProductEntityById(id);
        _orderProductRepository.delete(deleteOrderProductEntity);
        return _orderProductMapper.toDto(deleteOrderProductEntity);
    }

    private OrderProduct getOrderProductEntityById(Long id){
        return _orderProductRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Order product not found with id: " + id)
                );
    }

    private void validate(Object obj){
        _validator.validate(obj);
    }
}
