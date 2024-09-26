package co.shop.api.interfaces.mappers;

import co.shop.api.dtos.productDto.CreateProductDto;
import co.shop.api.dtos.productDto.ProductDto;
import co.shop.api.entities.Product;

public interface IProductMapper {
    ProductDto toDto(Product product);
    Product fromCreateDtoToEntity(CreateProductDto createProductDto);
}
