package co.shop.api.dtos.opinionDto;

import co.shop.api.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateOpinionDto {
    private String comment;
    private float rating;
    private Long productId;
    @JsonIgnore
    private Product product;
}
