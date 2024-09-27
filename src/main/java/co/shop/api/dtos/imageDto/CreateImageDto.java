package co.shop.api.dtos.imageDto;

import co.shop.api.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateImageDto {

    @NotBlank(message = "Nazwa zdjęcia nie powinna być pusta!")
    private String imageName;

    @NotBlank(message = "Id produktu nie powinno być puste!")
    private Long productId;
    @JsonIgnore
    private Product product;
}
