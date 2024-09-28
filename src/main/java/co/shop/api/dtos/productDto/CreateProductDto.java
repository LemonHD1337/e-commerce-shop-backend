package co.shop.api.dtos.productDto;

import co.shop.api.entities.enums.Category;
import co.shop.api.entities.enums.Color;
import co.shop.api.entities.enums.DressStyle;
import jakarta.validation.constraints.*;
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

    @DecimalMin(value = "10", message = "Cena produktu nie powinna być mniejsza niz 10")
    private BigDecimal price;

    @Min(value = 1, message = "Nakład produktu nie powinna być mniejsza mniejszy niż 1")
    private int quantity;

    private Category category;
    private co.shop.api.entities.enums.Size size;
    private Color color;
    private DressStyle dressStyle;
}
