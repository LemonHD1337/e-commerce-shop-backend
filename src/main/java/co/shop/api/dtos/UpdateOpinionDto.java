package co.shop.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateOpinionDto {
    private String comment;
    private float rating;
    private Long productId;
}
