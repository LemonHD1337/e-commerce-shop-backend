package co.shop.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressDto{
    private String houseNumber;
    private String streetName;
    private String city;
    private String zipcode;
}
