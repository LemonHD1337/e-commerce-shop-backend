package co.shop.api.dtos.opinionDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateOpinionDto {

    @NotBlank(message = "Opinia nie może być pusta!")
    @Size(min = 5, max = 150, message = "Opinia powinna zawierać od 5 do 150 znaków")
    private String comment;

    @NotBlank(message = "Ocena produktu nie powinna być pusta!")
    private float rating;

    private Long productId;
}
