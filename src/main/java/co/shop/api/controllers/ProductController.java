package co.shop.api.controllers;


import co.shop.api.dtos.CreateProductDto;
import co.shop.api.dtos.ProductDto;
import co.shop.api.dtos.UpdateProductDto;
import co.shop.api.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService _productService;

    public ProductController(ProductService productService) {
        this._productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> GetAllProducts() {
        return ResponseEntity.ok(_productService.getAllProduct());
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<ProductDto> GetProductById(@PathVariable final Long id) {
        return ResponseEntity.ok(_productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> CreateProduct(@RequestBody final CreateProductDto createProductDto) {
        return ResponseEntity.ok(_productService.createProduct(createProductDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> UpdateProduct(@RequestBody final UpdateProductDto updateProductDto, @PathVariable final Long id) {
        return ResponseEntity.ok(_productService.updateProduct(updateProductDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteProduct(@PathVariable final Long id) {
        var productDto = _productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
