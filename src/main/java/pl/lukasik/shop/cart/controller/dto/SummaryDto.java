package pl.lukasik.shop.cart.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Builder
public class SummaryDto {

    private BigDecimal grossValue;
}
