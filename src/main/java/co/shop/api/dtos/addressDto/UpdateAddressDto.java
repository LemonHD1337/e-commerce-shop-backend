package co.shop.api.dtos.addressDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAddressDto {

    @NotBlank(message = "Numer domu nie powinnien być pusty!")
    @Size(min = 1, max = 20, message = "Numer domu musi zawierać od 1 do 20 znaków")
    private String houseNumber;

    @NotBlank(message = "Nazwa ulicy nie powinnien być pusty!")
    @Size(min = 3, max = 40, message = "Nazwa ulicy musi zawierać od 3 do 40 znaków")
    private String streetName;

    @NotBlank(message = "Nazwa miasta nie powinnien być pusty!")
    @Size(min = 3, max = 40, message = "Nazwa miasta musi zawierać od 3 do 40 znaków")
    private String city;

    @NotBlank(message = "Kod pocztowy nie powinnien być pusty!")
    @Pattern(regexp = "^\\d{2}-\\d{3}$", message = "Zły format kodu pocztowego xx-xxx")
    private String zipcode;
}
