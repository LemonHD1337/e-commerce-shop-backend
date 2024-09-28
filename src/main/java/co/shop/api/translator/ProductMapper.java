package co.shop.api.translator;


import co.shop.api.dtos.productDto.CreateProductDto;
import co.shop.api.dtos.productDto.ProductDto;
import co.shop.api.dtos.productDto.UpdateProductDto;
import co.shop.api.entities.Product;
import co.shop.api.interfaces.mappers.IProductMapper;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper implements IProductMapper {

    @Override
    public ProductDto toDto(Product product) {
        var productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setCategory(product.getCategory());
        productDto.setColor(product.getColor());
        productDto.setSize(product.getSize());
        productDto.setDressStyle(product.getDressStyle());
        productDto.setQuantitySold(product.getQuantitySold());
        return productDto;
    }

    @Override
    public Product fromCreateDtoToEntity(CreateProductDto createProductDto) {
        var productEntity = new Product();

        productEntity.setName(createProductDto.getName());
        productEntity.setDescription(createProductDto.getDescription());
        productEntity.setPrice(createProductDto.getPrice());
        productEntity.setQuantity(createProductDto.getQuantity());
        productEntity.setCategory(createProductDto.getCategory());
        productEntity.setColor(createProductDto.getColor());
        productEntity.setSize(createProductDto.getSize());
        productEntity.setDressStyle(createProductDto.getDressStyle());

        return productEntity;
    }

    @Override
    public Product fromUpdateProductDtoToEntity(Product productFromDb, UpdateProductDto updateProductDto) {
        productFromDb.setName(updateProductDto.getName());
        productFromDb.setDescription(updateProductDto.getDescription());
        productFromDb.setPrice(updateProductDto.getPrice());
        productFromDb.setQuantity(updateProductDto.getQuantity());
        productFromDb.setCategory(updateProductDto.getCategory());
        productFromDb.setColor(updateProductDto.getColor());
        productFromDb.setSize(updateProductDto.getSize());
        productFromDb.setDressStyle(updateProductDto.getDressStyle());

        return productFromDb;
    }
}
