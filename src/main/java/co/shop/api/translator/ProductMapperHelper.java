package co.shop.api.translator;

import co.shop.api.entities.Product;
import co.shop.api.exception.ResourceNotFoundException;
import co.shop.api.repositories.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperHelper {
    private final ProductRepository _productRepository;

    public ProductMapperHelper(final ProductRepository productRepository) {
        this._productRepository = productRepository;
    }

    public Product mapProduct(Long id){
        return _productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }
}
