package pl.lukasik.shop.order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.lukasik.shop.order.model.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderListDto {

    private Long id;
    private LocalDateTime placeDate;
    private OrderStatus orderStatus;
    private BigDecimal grossValue;
}
