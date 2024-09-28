package co.shop.api.interfaces.services;

import co.shop.api.dtos.addressDto.AddressDto;
import co.shop.api.dtos.addressDto.CreateAddressDto;
import co.shop.api.dtos.addressDto.UpdateAddressDto;


import java.util.List;

public interface IAddressService {
    List<AddressDto> getAll();
    AddressDto getById(Long id);
    AddressDto create(CreateAddressDto createAddressDto);
    AddressDto update(Long id,UpdateAddressDto updateAddressDto);
    AddressDto delete(Long id);
}
