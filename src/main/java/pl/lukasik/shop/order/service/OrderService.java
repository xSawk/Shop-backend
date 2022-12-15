package pl.lukasik.shop.order.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lukasik.shop.common.model.Cart;
import pl.lukasik.shop.common.model.CartItem;
import pl.lukasik.shop.common.repository.CartItemRepository;
import pl.lukasik.shop.common.repository.CartRepository;
import pl.lukasik.shop.order.model.Order;
import pl.lukasik.shop.order.model.OrderRow;
import pl.lukasik.shop.order.model.OrderStatus;
import pl.lukasik.shop.order.model.dto.OrderDto;
import pl.lukasik.shop.order.model.dto.OrderSummary;
import pl.lukasik.shop.order.repository.OrderRepository;
import pl.lukasik.shop.order.repository.OrderRowRepository;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartItemRepository cartItemRepository;


    public OrderService(OrderRepository orderRepository, CartRepository cartRepository, OrderRowRepository orderRowRepository, CartItemRepository cartItemRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.orderRowRepository = orderRowRepository;
        this.cartItemRepository = cartItemRepository;
    }


    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Order order = Order.builder()
                .firstname(orderDto.getFirstname())
                .lastname(orderDto.getLastname())
                .street(orderDto.getStreet())
                .zipcode(orderDto.getZipcode())
                .city(orderDto.getCity())
                .email(orderDto.getEmail())
                .phone(orderDto.getPhone())
                .placeDate(LocalDateTime.now())
                .orderStatus(OrderStatus.New)
                .grossValue(calculateGrossValue(cart.getItems()))
                .build();
        Order newOrder = orderRepository.save(order);

        saveOrderRows(cart, newOrder.getId());
        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteCartById(orderDto.getCartId());


        return OrderSummary.builder()
                .id(newOrder.getId())
                .status(newOrder.getOrderStatus())
                .placeDate(newOrder.getPlaceDate())
                .grossValue(newOrder.getGrossValue())
                .build();

    }

    private BigDecimal calculateGrossValue(List<CartItem> items) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(
                        BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

    }


    public void saveOrderRows(Cart cart, Long id){
        List<OrderRow> orderRows = cart.getItems().stream()
                .map(cartItem -> OrderRow.builder()
                        .quantity(cartItem.getQuantity())
                        .productId(cartItem.getProduct().getId())
                        .price(cartItem.getProduct().getPrice())
                        .orderId(id)

                        .build()
                ).toList();

        orderRowRepository.saveAll(orderRows);

    }
}
