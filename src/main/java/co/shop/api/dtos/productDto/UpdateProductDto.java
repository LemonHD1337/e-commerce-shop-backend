package co.shop.api.dtos.productDto;

import co.shop.api.entities.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDto {
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private Category category;
}
