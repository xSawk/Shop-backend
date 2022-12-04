package pl.lukasik.shop.admin.category.controller.dto;


import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class AdminCategoryDto {

    private Long id;
    @NotBlank
    @Size(min=4)
    private String name;
    private String description;

}
