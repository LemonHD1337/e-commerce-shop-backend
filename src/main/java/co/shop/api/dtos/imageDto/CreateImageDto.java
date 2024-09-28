package co.shop.api.dtos.imageDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateImageDto {

    @NotBlank(message = "Nazwa zdjęcia nie powinna być pusta!")
    private String imageName;

    private Long productId;
}
