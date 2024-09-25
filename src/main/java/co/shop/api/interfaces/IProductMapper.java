package co.shop.api.interfaces;

import co.shop.api.dtos.CreateProductDto;
import co.shop.api.dtos.ProductDto;
import co.shop.api.entities.Product;

public interface IProductMapper {
    ProductDto toDto(Product product);
    Product fromCreateDtoToEntity(CreateProductDto createProductDto);
}
