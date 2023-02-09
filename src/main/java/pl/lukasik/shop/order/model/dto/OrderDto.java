package pl.lukasik.shop.order.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
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
    @NotNull
    private Long shipmentId;
    @NotNull
    private Long paymentId;

}
