package co.shop.api.translator;

import co.shop.api.dtos.ProductDto;
import co.shop.api.entities.Product;
import org.springframework.stereotype.Service;



@Service
public class ProductMapper{

    public ProductDto fromProductModelToProductDto(Product product) {
        return new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                product.getQuantity(),
                product.getCategory()
                );
    }
}
