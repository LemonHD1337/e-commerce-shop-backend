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
        return _productMapper.toDto(getProductEntityById(id));
    }

    public ProductDto createProduct(CreateProductDto createProductDto){
        validate(createProductDto);
        var createdProduct = _productRepository.save(_productMapper.fromCreateDtoToEntity(createProductDto));
        return _productMapper.toDto(createdProduct);
    }


    public ProductDto updateProduct(Long id, UpdateProductDto updateProductDto) {
        validate(updateProductDto);
        var product = getProductEntityById(id);
        var changedProduct = _productMapper.fromUpdateProductDtoToEntity(product, updateProductDto);
        return _productMapper.toDto(_productRepository.save(changedProduct));
    }

    public ProductDto deleteProduct(Long id) {
        var product = getProductEntityById(id);
        _productRepository.delete(product);
        return _productMapper.toDto(product);
    }

    private Product getProductEntityById(Long id){
        return _productRepository
                .findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException("Product not found with id: " + id)
                );
    }

    private void validate(Object obj){
        _validator.validate(obj);
    }
}
