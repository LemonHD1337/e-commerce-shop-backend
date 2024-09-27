package co.shop.api.dtos.productDto;

import co.shop.api.entities.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateProductDto {

    @NotBlank(message = "Nazwa produktu nie może być pusta!")
    @Size(min = 3, max = 50, message = "Nazwa produktu musi zawierać od 3 do 50 znaków")
    private String name;

    @NotBlank(message = "Opis produktu nie może być pusta!")
    @Size(min = 3, max = 120, message = "Opis produktu musi zawierać od 3 do 120 znaków")
    private String description;

    @NotBlank(message = "Cena produktu nie może być pusta!")
    private BigDecimal price;

    @NotBlank(message = "Nakład produktu nie może być pusty")
    private int quantity;

    @NotBlank(message = "Kategoria produktu nie może być pusta!")
    private Category category;
}
