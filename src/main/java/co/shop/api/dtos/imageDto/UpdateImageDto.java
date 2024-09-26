package co.shop.api.dtos.imageDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateImageDto {
    private String imageName;
    private Long productId;
}
