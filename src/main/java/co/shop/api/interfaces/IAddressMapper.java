package co.shop.api.interfaces;

import co.shop.api.dtos.AddressDto;
import co.shop.api.dtos.CreateAddressDto;
import co.shop.api.entities.Address;

public interface IAddressMapper {
    AddressDto toDto(Address address);
    Address fromCreateAddressDtoToEntity(CreateAddressDto createAddressDto);
}
