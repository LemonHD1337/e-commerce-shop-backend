package co.shop.api.translator;

import co.shop.api.dtos.addressDto.AddressDto;
import co.shop.api.dtos.addressDto.CreateAddressDto;
import co.shop.api.dtos.addressDto.UpdateAddressDto;
import co.shop.api.entities.Address;
import co.shop.api.interfaces.mappers.IAddressMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements IAddressMapper {

    @Override
    public AddressDto toDto(Address address) {
        var addressDto = new AddressDto();

        addressDto.setId(address.getId());
        addressDto.setHouseNumber(address.getHouseNumber());
        addressDto.setStreetName(address.getStreetName());
        addressDto.setCity(address.getCity());
        addressDto.setZipcode(address.getZipcode());

        return addressDto;
    }

    @Override
    public Address fromCreateAddressDtoToEntity(CreateAddressDto createAddressDto) {
        var addressEntity = new Address();

        addressEntity.setHouseNumber(createAddressDto.getHouseNumber());
        addressEntity.setStreetName(createAddressDto.getStreetName());
        addressEntity.setCity(createAddressDto.getCity());
        addressEntity.setZipcode(createAddressDto.getZipcode());

        return addressEntity;
    }

    @Override
    public Address fromUpdateAddressDtoToEntity(Address addressFromDb, UpdateAddressDto updateAddressDto) {
        addressFromDb.setHouseNumber(updateAddressDto.getHouseNumber());
        addressFromDb.setStreetName(updateAddressDto.getStreetName());
        addressFromDb.setCity(updateAddressDto.getCity());
        addressFromDb.setZipcode(updateAddressDto.getZipcode());
        return addressFromDb;
    }
}
