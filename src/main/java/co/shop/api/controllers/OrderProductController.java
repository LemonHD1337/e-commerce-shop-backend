package co.shop.api.controllers;

import co.shop.api.dtos.orderProductDto.CreateOrderProductDto;
import co.shop.api.dtos.orderProductDto.OrderProductDto;
import co.shop.api.dtos.orderProductDto.UpdateOrderProductDto;
import co.shop.api.interfaces.services.IOrderProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order_product")
public class OrderProductController {
    private final IOrderProductService _orderProductService;

    public OrderProductController(IOrderProductService orderProductService) {
        this._orderProductService = orderProductService;
    }

    @GetMapping
    public ResponseEntity<List<OrderProductDto>> getAll() {
        return ResponseEntity.ok(_orderProductService.getOrderProducts());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<OrderProductDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(_orderProductService.getOrderProductById(id));
    }

    @PostMapping
    public ResponseEntity<OrderProductDto> create(@RequestBody CreateOrderProductDto createOrderProductDto) {
        return ResponseEntity.ok(_orderProductService.createOrderProduct(createOrderProductDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderProductDto> update(@PathVariable Long id, @RequestBody UpdateOrderProductDto updateOrderProductDto) {
        return ResponseEntity.ok(_orderProductService.updateOrderProduct(id, updateOrderProductDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var deletedOrderProduct = _orderProductService.deleteOrderProduct(id);

        return ResponseEntity.noContent().build();
    }
}
