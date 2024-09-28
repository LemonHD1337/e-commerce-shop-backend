package co.shop.api.translator;

import co.shop.api.dtos.orderProductDto.CreateOrderProductDto;
import co.shop.api.dtos.orderProductDto.OrderProductDto;
import co.shop.api.dtos.orderProductDto.UpdateOrderProductDto;
import co.shop.api.entities.Order;
import co.shop.api.entities.OrderProduct;
import co.shop.api.entities.Product;
import co.shop.api.interfaces.mappers.IOrderProductMapper;
import co.shop.api.interfaces.mappers.IProductMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderProductMapper implements IOrderProductMapper {

    private final IProductMapper _productMapper;


    public OrderProductMapper(IProductMapper productMapper) {
        this._productMapper = productMapper;
    }

    @Override
    public OrderProductDto toDto(OrderProduct orderProduct) {
        var orderProductDto = new OrderProductDto();
        var productDto = _productMapper.toDto(orderProduct.getProduct());

        orderProductDto.setId(orderProduct.getId());
        orderProductDto.setQuantity(orderProduct.getQuantity());
        orderProductDto.setProduct(productDto);

        return orderProductDto;
    }

    @Override
    public OrderProduct fromCreateOrderProductDtoToEntity(CreateOrderProductDto createOrderProductDto) {
        var orderProduct = new OrderProduct();
        var order = new Order();
        var product = new Product();

        product.setId(createOrderProductDto.getProductId());
        order.setId(createOrderProductDto.getOrderId());

        orderProduct.setQuantity(createOrderProductDto.getQuantity());
        orderProduct.setProduct(product);
        orderProduct.setOrder(order);

        return orderProduct;
    }

    @Override
    public OrderProduct fromUpdateOrderProductDtoToEntity(OrderProduct orderProductFromDb, UpdateOrderProductDto updateOrderProductDto) {
        var product = new Product();
        var order = new Order();
        product.setId(updateOrderProductDto.getProductId());
        order.setId(updateOrderProductDto.getOrderId());

        orderProductFromDb.setQuantity(updateOrderProductDto.getQuantity());
        orderProductFromDb.setProduct(product);
        orderProductFromDb.setOrder(order);

        return orderProductFromDb;
    }
}
