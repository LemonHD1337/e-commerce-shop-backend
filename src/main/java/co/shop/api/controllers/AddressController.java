package co.shop.api.controllers;

import co.shop.api.dtos.addressDto.AddressDto;
import co.shop.api.dtos.addressDto.CreateAddressDto;
import co.shop.api.dtos.addressDto.UpdateAddressDto;
import co.shop.api.interfaces.services.IAddressService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final IAddressService _addressService;

    public AddressController(IAddressService addressService) {
        this._addressService = addressService;
    }


    @GetMapping
    public ResponseEntity<List<AddressDto>> getAll() {
        return ResponseEntity.ok(_addressService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(_addressService.getById(id));
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody CreateAddressDto createAddressDto, HttpServletRequest request) {
        var createdAddress = _addressService.create(createAddressDto);

        URI location = ServletUriComponentsBuilder
                .fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(createdAddress.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@RequestBody UpdateAddressDto updateAddressDto, @PathVariable Long id) {
        return ResponseEntity.ok(_addressService.update(id, updateAddressDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        _addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
