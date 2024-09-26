package co.shop.api.translator;


import co.shop.api.dtos.productDto.CreateProductDto;
import co.shop.api.dtos.productDto.ProductDto;
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
        return productEntity;
    }
}
