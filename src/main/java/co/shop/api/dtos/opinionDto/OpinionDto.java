package co.shop.api.dtos.opinionDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OpinionDto {
    private Long id;
    private String comment;
    private float rating;
    private Long productId;
}
