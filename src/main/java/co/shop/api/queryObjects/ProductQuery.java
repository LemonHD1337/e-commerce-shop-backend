package co.shop.api.queryObjects;

import co.shop.api.entities.enums.Category;
import co.shop.api.entities.enums.Color;
import co.shop.api.entities.enums.DressStyle;
import co.shop.api.entities.enums.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductQuery {
    private Category category;
    private Size size;
    private Color color;
    private DressStyle dressStyle;
    private double minPrice = 0;
    private double maxPrice = 20000;
    private String productName;
    private Boolean isDescending;
    private SortByProduct sortBy;
    private int page = 1;
    private int pageSize = 20;
}
