package co.shop.api.dtos.imageDto;

import co.shop.api.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateImageDto {
    private String imageName;
    private Long productId;
    @JsonIgnore
    private Product product;
}
