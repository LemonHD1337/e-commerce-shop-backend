package co.shop.api.services;

import co.shop.api.dtos.productDto.CreateProductDto;
import co.shop.api.dtos.productDto.ProductDto;
import co.shop.api.dtos.productDto.UpdateProductDto;
import co.shop.api.entities.Product;
import co.shop.api.interfaces.mappers.IProductMapper;
import co.shop.api.interfaces.services.IProductService;
import co.shop.api.queryObjects.ProductQuery;
import co.shop.api.repositories.ProductRepository;
import co.shop.api.exception.ResourceNotFoundException;
import co.shop.api.validation.CentralValidator;
import jakarta.persistence.criteria.*;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService implements IProductService {

    private final ProductRepository _productRepository;
    private final IProductMapper _productMapper;
    private final CentralValidator _validator;
    private final EntityManager _entityManager;

    public ProductService(
            ProductRepository productRepository,
            IProductMapper productMapper,
            CentralValidator validator,
            EntityManager entityManager
    ) {
        this._productRepository = productRepository;
        this._productMapper =  productMapper;
        this._validator = validator;
        this._entityManager = entityManager;
    }

    @Override
    public List<ProductDto> getAllProduct(ProductQuery query){
        CriteriaBuilder criteriaBuilder = _entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        if(query.getSortBy() != null && !query.getSortBy().toString().trim().isEmpty()){
            if(query.getSortBy().toString().equals("PRICE")){
                if(query.getIsDescending()){
                    orders.add(criteriaBuilder.desc(root.get("price")));
                }
                orders.add(criteriaBuilder.asc(root.get("price")));
            }
            if(query.getSortBy().toString().equals("POPULARITY")){
                if(query.getIsDescending()){
                    orders.add(criteriaBuilder.desc(root.get("quantitySold")));
                }
                orders.add(criteriaBuilder.asc(root.get("quantitySold")));
            }
            if(query.getSortBy().toString().equals("DATE")){
                if(query.getIsDescending()){
                    orders.add(criteriaBuilder.desc(root.get("createdAt")));
                }
                orders.add(criteriaBuilder.asc(root.get("createdAt")));
            }
        }

        if(query.getProductName() != null && !query.getProductName().isEmpty()){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + query.getProductName() + "%"));
        }

        if(query.getSize() != null && !query.getSize().toString().trim().isEmpty()){
            predicates.add(criteriaBuilder.equal(root.get("size"), query.getSize()));
        }

        if(query.getCategory() != null && !query.getCategory().toString().trim().isEmpty()){
            predicates.add(criteriaBuilder.equal(root.get("category"), query.getCategory()));
        }

        if(query.getColor() != null && !query.getColor().toString().trim().isEmpty()){
            predicates.add(criteriaBuilder.equal(root.get("color"), query.getColor()));
        }

        if(query.getDressStyle() != null && !query.getDressStyle().toString().trim().isEmpty()){
            predicates.add(criteriaBuilder.equal(root.get("dressStyle"), query.getDressStyle()));
        }

        predicates.add(criteriaBuilder.greaterThan(root.get("price"), query.getMinPrice()));
        predicates.add(criteriaBuilder.lessThan(root.get("price"), query.getMaxPrice()));

        criteriaQuery.where(predicates.toArray(new Predicate[0])).orderBy(orders);


        return _entityManager
                .createQuery(criteriaQuery)
                .setFirstResult((query.getPage()-1) * query.getPageSize())
                .setMaxResults(query.getPageSize())
                .getResultList()
                .stream()
                .map(_productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto getProductById(Long id){
        return _productMapper.toDto(getProductEntityById(id));
    }

    @Override
    public ProductDto createProduct(CreateProductDto createProductDto){
        validate(createProductDto);
        var createdProduct = _productRepository.save(_productMapper.fromCreateDtoToEntity(createProductDto));
        return _productMapper.toDto(createdProduct);
    }


    @Override
    public ProductDto updateProduct(Long id, UpdateProductDto updateProductDto) {
        validate(updateProductDto);
        var product = getProductEntityById(id);
        var changedProduct = _productMapper.fromUpdateProductDtoToEntity(product, updateProductDto);
        return _productMapper.toDto(_productRepository.save(changedProduct));
    }

    @Override
    public ProductDto deleteProduct(Long id) {
        var product = getProductEntityById(id);
        _productRepository.delete(product);
        return _productMapper.toDto(product);
    }

    @Override
    public long countProduct() {
        return _productRepository.count();
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
