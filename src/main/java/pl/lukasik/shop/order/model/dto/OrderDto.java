package pl.lukasik.shop.order.model.dto;


import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class OrderDto {
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String street;
    @NotBlank
    private String zipcode;
    @NotBlank
    private String city;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
    @NotNull
    private Long cartId;

}
