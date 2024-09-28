package co.shop.api.dtos.productDto;

import co.shop.api.entities.enums.Category;
import co.shop.api.entities.enums.Color;
import co.shop.api.entities.enums.DressStyle;
import co.shop.api.entities.enums.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private Category category;
    private Size size;
    private Color color;
    private DressStyle dressStyle;
    private int quantitySold;
}
