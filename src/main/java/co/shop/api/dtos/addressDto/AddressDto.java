package co.shop.api.dtos.addressDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private Long id;
    private String houseNumber;
    private String streetName;
    private String city;
    private String zipcode;
}
