package co.shop.api.interfaces.mappers;

import co.shop.api.dtos.addressDto.AddressDto;
import co.shop.api.dtos.addressDto.CreateAddressDto;
import co.shop.api.entities.Address;

public interface IAddressMapper {
    AddressDto toDto(Address address);
    Address toEntity(AddressDto addressDto);
    Address fromCreateAddressDtoToEntity(CreateAddressDto createAddressDto);
}
