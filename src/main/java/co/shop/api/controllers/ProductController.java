package co.shop.api.controllers;

import co.shop.api.dtos.productDto.CreateProductDto;
import co.shop.api.dtos.productDto.ProductDto;
import co.shop.api.dtos.productDto.UpdateProductDto;
import co.shop.api.interfaces.services.IProductService;
import co.shop.api.queryObjects.ProductQuery;
import jakarta.servlet.http.HttpServletRequest;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final IProductService _productService;

    public ProductController(IProductService productService) {
        this._productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> GetAllProducts(@ParameterObject ProductQuery query) {
        return ResponseEntity
                .ok()
                .header(
                        "X-Total-Count",
                        String.valueOf(_productService.countProduct())
                )
                .body(_productService.getAllProduct(query));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> GetProductById(@PathVariable final Long id) {
        return ResponseEntity.ok(_productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> CreateProduct(
            @RequestBody final CreateProductDto createProductDto,
            HttpServletRequest request
    ) {
       var createdProduct = _productService.createProduct(createProductDto);
        URI location = ServletUriComponentsBuilder.fromRequest(request)
                .path("/{id}").buildAndExpand(createdProduct.getId()).toUri();

        return ResponseEntity.created(location).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> UpdateProduct(@RequestBody final UpdateProductDto updateProductDto, @PathVariable final Long id) {
        return ResponseEntity.ok(_productService.updateProduct(id, updateProductDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteProduct(@PathVariable final Long id) {
        _productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
