package pl.lukasik.shop.order.service.mapper;

import pl.lukasik.shop.order.model.Order;
import pl.lukasik.shop.order.model.dto.OrderListDto;

import java.util.List;

public class OrderDtoMapper {

    public static List<OrderListDto> mapToOrderListDto(List<Order> orders) {
        return orders.stream()
                .map(order -> new OrderListDto(order.getId(),
                     order.getPlaceDate(),
                     order.getOrderStatus(),
                     order.getGrossValue()))
                .toList();
    }
}
