package pl.lukasik.shop.cart.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
@Builder
public class ProductDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private String currency;
    private String image;
}
