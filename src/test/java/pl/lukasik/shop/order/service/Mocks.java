package pl.lukasik.shop.order.service;

import pl.lukasik.shop.common.model.Cart;
import pl.lukasik.shop.common.model.CartItem;
import pl.lukasik.shop.common.model.Product;
import pl.lukasik.shop.order.model.Payment;
import pl.lukasik.shop.order.model.PaymentType;
import pl.lukasik.shop.order.model.Shipment;
import pl.lukasik.shop.order.model.dto.OrderDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Mocks {
    public static OrderDto createDto() {
        return OrderDto.builder()
                .firstname("Jan")
                .lastname("Kowalski")
                .street("Cicha")
                .zipcode("66-000")
                .city("Ploty")
                .email("test@gmail.com")
                .phone("555555555")
                .cartId(1L)
                .shipmentId(2L)
                .paymentId(3L)
                .build();
    }

    public static Optional<Payment> createPayment() {
        return Optional.of(
                Payment.builder()
                        .id(1L)
                        .name("paypal")
                        .type(PaymentType.TRANSFER_BANK)
                        .defaultPayment(true)
                        .build()
        );
    }

    public static Optional<Shipment> createShipment() {
        return Optional.of(
                Shipment.builder()
                        .id(2L)
                        .price(new BigDecimal("10.00"))
                        .build()
        );
    }

    public static Optional<Cart> createCart() {
        return Optional.of(Cart.builder()
                .id(1L)
                .created(LocalDateTime.now())
                .items(createItems())
                .build());
    }

    public static List<CartItem> createItems() {
        return List.of(
                CartItem.builder()
                        .id(1L)
                        .cartId(1L)
                        .quantity(1)
                        .product(Product.builder()
                                .id(1L)
                                .price(new BigDecimal("2.50"))
                                .build())
                        .build(),
                CartItem.builder()
                        .id(2L)
                        .cartId(1L)
                        .quantity(2)
                        .product(Product.builder()
                                .id(2L)
                                .price(new BigDecimal("3.00"))
                                .build())
                        .build()
        );

    }
}
