package co.shop.api.services;

import co.shop.api.dtos.addressDto.AddressDto;
import co.shop.api.dtos.addressDto.CreateAddressDto;
import co.shop.api.dtos.addressDto.UpdateAddressDto;
import co.shop.api.exception.ResourceNotFoundException;
import co.shop.api.interfaces.mappers.IAddressMapper;
import co.shop.api.interfaces.services.IAddressService;
import co.shop.api.repositories.AddressRepository;
import co.shop.api.validation.CentralValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddressService {

    private final AddressRepository _addressRepository;
    private final IAddressMapper _addressMapper;
    private final CentralValidator _validator;

    public AddressService(
            AddressRepository addressRepository,
            IAddressMapper addressMapper,
            CentralValidator validator
    ) {
        this._addressRepository = addressRepository;
        this._addressMapper = addressMapper;
        this._validator = validator;
    }

    @Override
    public List<AddressDto> getAll() {
        return _addressRepository.findAll().stream().map(_addressMapper::toDto).toList();
    }

    @Override
    public AddressDto getById(Long id) {
        return _addressRepository
                .findById(id)
                .map(_addressMapper::toDto)
                .orElseThrow(
                    () -> new ResourceNotFoundException("Address not found with id: " + id)
                );
    }

    @Override
    public AddressDto create(CreateAddressDto createAddressDto) {
        _validator.validate(createAddressDto);

        var address = _addressRepository.save(_addressMapper.fromCreateAddressDtoToEntity(createAddressDto));

        return _addressMapper.toDto(address);
    }

    @Override
    public AddressDto update(Long id,UpdateAddressDto updateAddressDto) {
        _validator.validate(updateAddressDto);

        var addressEntity = _addressRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Address not found with id: " + id)
                );


        addressEntity.setHouseNumber(updateAddressDto.getHouseNumber());
        addressEntity.setStreetName(updateAddressDto.getStreetName());
        addressEntity.setCity(updateAddressDto.getCity());
        addressEntity.setZipcode(updateAddressDto.getZipcode());

        _addressRepository.save(addressEntity);

        return _addressMapper.toDto(addressEntity);
    }

    @Override
    public AddressDto delete(Long id) {
        var addressEntity = _addressRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Address not found with id: " + id)
                );

        _addressRepository.delete(addressEntity);

        return _addressMapper.toDto(addressEntity);
    }
}
