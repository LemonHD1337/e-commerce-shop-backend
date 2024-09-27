package co.shop.api.translator;

import co.shop.api.dtos.addressDto.AddressDto;
import co.shop.api.dtos.addressDto.CreateAddressDto;
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
    public Address toEntity(AddressDto addressDto) {
        var address = new Address();

        address.setId(addressDto.getId());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setStreetName(addressDto.getStreetName());
        address.setCity(addressDto.getCity());
        address.setZipcode(addressDto.getZipcode());

        return address;
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
}
