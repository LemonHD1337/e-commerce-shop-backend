package co.shop.api.services;

import co.shop.api.dtos.ProductDto;
import co.shop.api.dtos.UpdateProductDto;
import co.shop.api.entities.Product;
import co.shop.api.repositories.ProductRepository;
import co.shop.api.translator.ProductMapper;
import exception.EmptyRequestBodyException;
import exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository _productRepository;
    private final ProductMapper _productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this._productRepository = productRepository;
        this._productMapper =  productMapper;
    }

    public List<ProductDto> getAllProduct(){
        List<Product> products = _productRepository.findAll();
        return products.stream().map(_productMapper::fromProductModelToProductDto).toList();
    }

    public ProductDto getProductById(Long id){
        return _productRepository.findById(id)
                .map(_productMapper::fromProductModelToProductDto)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Product not found with id: " + id)
                );
    }

    public ProductDto createProduct(Product product){
        if(product == null) throw new EmptyRequestBodyException("data is missing");
        _productRepository.save(product);
        return _productMapper.fromProductModelToProductDto(product);
    }


    public ProductDto updateProduct(UpdateProductDto updateProductDto, Long id) {
        if(updateProductDto == null) throw new EmptyRequestBodyException("data is missing");
        Product product = _productRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product not found with id: " + id)
                );

        product.setName(updateProductDto.getName());
        product.setDescription(updateProductDto.getDescription());
        product.setPrice(updateProductDto.getPrice());
        product.setCategory(updateProductDto.getCategory());
        product.setQuantity(updateProductDto.getQuantity());
        _productRepository.save(product);
        return _productMapper.fromProductModelToProductDto(product);
    }

    public ProductDto deleteProduct(Long id) {
        var product = _productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id: " + id)
        );

        _productRepository.delete(product);
        return _productMapper.fromProductModelToProductDto(product);
    }
}
