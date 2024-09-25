package co.shop.api.interfaces;

import co.shop.api.dtos.AddressDto;
import co.shop.api.dtos.CreateAddressDto;
import co.shop.api.dtos.UpdateAddressDto;

import java.util.List;

public interface IAddressService {
    List<AddressDto> getAll();
    AddressDto getById(Long id);
    AddressDto create(CreateAddressDto createAddressDto);
    AddressDto update(Long id,UpdateAddressDto updateAddressDto);
    AddressDto delete(Long id);
}
