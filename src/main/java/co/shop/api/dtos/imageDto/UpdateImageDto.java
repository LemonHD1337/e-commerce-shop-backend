package co.shop.api.dtos.imageDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateImageDto {

    @NotBlank(message = "Nazwa zdjęcia nie powinna być pusta!")
    private String imageName;

    @NotBlank(message = "Id produktu nie powinno być puste!")
    private Long productId;
}
