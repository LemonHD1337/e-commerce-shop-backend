package co.shop.api.controllers;

import co.shop.api.dtos.orderDto.CreateOrderDto;
import co.shop.api.dtos.orderDto.OrderDto;
import co.shop.api.dtos.orderDto.UpdateOrderDto;
import co.shop.api.interfaces.services.IOrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final IOrderService _orderService;

    public OrderController(IOrderService orderService) {
        this._orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll(){
        return ResponseEntity.ok(_orderService.getOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable long id){
        return ResponseEntity.ok(_orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody CreateOrderDto createOrderDto, HttpServletRequest request){
        var createdOrder = _orderService.createOrder(createOrderDto);

        URI location = ServletUriComponentsBuilder.fromRequest(request)
                .path("/{id}").buildAndExpand(createdOrder.getId()).toUri();

        return ResponseEntity.created(location).body(createdOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> update(@PathVariable Long id, @RequestBody UpdateOrderDto updateOrderDto){
        return ResponseEntity.ok(_orderService.updateOrder(id, updateOrderDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        _orderService.deleteOrder(id);

        return ResponseEntity.noContent().build();
    }
}
