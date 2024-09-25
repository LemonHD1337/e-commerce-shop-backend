package co.shop.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateImageDto {
    private String imageName;
    private Long productId;
}
