package co.shop.api.interfaces.mappers;

import co.shop.api.dtos.addressDto.AddressDto;
import co.shop.api.dtos.addressDto.CreateAddressDto;
import co.shop.api.dtos.addressDto.UpdateAddressDto;
import co.shop.api.entities.Address;

public interface IAddressMapper {
    AddressDto toDto(Address address);
    Address fromCreateAddressDtoToEntity(CreateAddressDto createAddressDto);
    Address fromUpdateAddressDtoToEntity(Address addressFromDb,UpdateAddressDto updateAddressDto);
}
