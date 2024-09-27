package co.shop.api.services;

import co.shop.api.dtos.productDto.CreateProductDto;
import co.shop.api.dtos.productDto.ProductDto;
import co.shop.api.dtos.productDto.UpdateProductDto;
import co.shop.api.entities.Product;
import co.shop.api.interfaces.mappers.IProductMapper;
import co.shop.api.interfaces.services.IProductService;
import co.shop.api.repositories.ProductRepository;
import co.shop.api.exception.ResourceNotFoundException;
import co.shop.api.validation.CentralValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductRepository _productRepository;
    private final IProductMapper _productMapper;
    private final CentralValidator _validator;

    public ProductService(ProductRepository productRepository, IProductMapper productMapper, CentralValidator validator) {
        this._productRepository = productRepository;
        this._productMapper =  productMapper;
        this._validator = validator;
    }

    public List<ProductDto> getAllProduct(){
        List<Product> products = _productRepository.findAll();
        return products
                .stream()
                .map(_productMapper::toDto)
                .toList();
    }

    public ProductDto getProductById(Long id){
        return _productRepository
                .findById(id)
                .map(_productMapper::toDto)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Product not found with id: " + id)
                );
    }

    public ProductDto createProduct(CreateProductDto createProductDto){
        _validator.validate(createProductDto);
        var product = _productRepository.save(_productMapper.fromCreateDtoToEntity(createProductDto));
        return _productMapper.toDto(product);
    }


    public ProductDto updateProduct(Long id, UpdateProductDto updateProductDto) {
        _validator.validate(updateProductDto);

        Product product = _productRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product not found with id: " + id)
                );

        product.setName(updateProductDto.getName());
        product.setDescription(updateProductDto.getDescription());
        product.setPrice(updateProductDto.getPrice());
        product.setCategory(updateProductDto.getCategory());
        product.setQuantity(updateProductDto.getQuantity());
        _productRepository.save(product);

        return _productMapper.toDto(product);
    }

    public ProductDto deleteProduct(Long id) {
        var product = _productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id: " + id)
        );

        _productRepository.delete(product);
        return _productMapper.toDto(product);
    }
}
