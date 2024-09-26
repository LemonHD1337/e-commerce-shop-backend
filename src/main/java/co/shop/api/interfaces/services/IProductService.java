package co.shop.api.interfaces.services;

import co.shop.api.dtos.productDto.CreateProductDto;
import co.shop.api.dtos.productDto.ProductDto;
import co.shop.api.dtos.productDto.UpdateProductDto;

import java.util.List;

public interface IProductService {
    List<ProductDto> getAllProduct();
    ProductDto getProductById(Long id);
    ProductDto createProduct(CreateProductDto createProductDto);
    ProductDto updateProduct(Long id, UpdateProductDto updateProductDto);
    ProductDto deleteProduct(Long id);
}
