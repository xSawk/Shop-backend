package pl.lukasik.shop.product.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private BigDecimal price;
    private String currency;
    private String image;
    private List<ReviewDto> reviews;
}

