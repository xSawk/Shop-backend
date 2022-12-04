package pl.lukasik.shop.admin.product.controller.dto;

import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class AdminProductDto {
    private String name;
    private Long categoryId;
    private String description;
    private BigDecimal price;
    private String currency;
    private String image;
}
