package co.shop.api.dtos.imageDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDto {
    private Long id;
    private String imageName;
    private Long productId;
}
