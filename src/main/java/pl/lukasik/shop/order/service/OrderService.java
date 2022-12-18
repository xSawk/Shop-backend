package pl.lukasik.shop.order.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lukasik.shop.common.mail.EmailService;
import pl.lukasik.shop.common.model.Cart;
import pl.lukasik.shop.common.model.CartItem;
import pl.lukasik.shop.common.repository.CartItemRepository;
import pl.lukasik.shop.common.repository.CartRepository;
import pl.lukasik.shop.order.model.*;
import pl.lukasik.shop.order.model.dto.OrderDto;
import pl.lukasik.shop.order.model.dto.OrderSummary;
import pl.lukasik.shop.order.repository.OrderRepository;
import pl.lukasik.shop.order.repository.OrderRowRepository;
import pl.lukasik.shop.order.repository.PaymentRepository;
import pl.lukasik.shop.order.repository.ShipmentRepository;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
    private final EmailService emailService;



    public OrderService(OrderRepository orderRepository, CartRepository cartRepository,
        OrderRowRepository orderRowRepository, CartItemRepository cartItemRepository,
        ShipmentRepository shipmentRepository, PaymentRepository paymentRepository,
        EmailService emailService) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.orderRowRepository = orderRowRepository;
        this.cartItemRepository = cartItemRepository;
        this.shipmentRepository = shipmentRepository;
        this.paymentRepository = paymentRepository;
        this.emailService = emailService;
    }


    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();
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
                .grossValue(calculateGrossValue(cart.getItems(), shipment))
                .payment(payment)
                .build();
        Order newOrder = orderRepository.save(order);
        saveOrderRows(cart, newOrder.getId(), shipment);
        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteCartById(orderDto.getCartId());
        emailService.send(order.getEmail(), "Twoje zamówienie zostało przyjęte", emailMsg(order));

        return OrderSummary.builder()
                .id(newOrder.getId())
                .status(newOrder.getOrderStatus())
                .placeDate(newOrder.getPlaceDate())
                .grossValue(newOrder.getGrossValue())
                .payment(payment)
                .build();

    }

    private String emailMsg(Order order) {
        return "Twoje zamówienie o id: " + order.getId() +
                "\nData złożenia: " + order.getPlaceDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                "\nWartość: " + order.getGrossValue() + " PLN " +
                "\n\n" +
                "\nPłatność: " + order.getPayment().getName() +
                (order.getPayment().getNote() != null ? "\n" + order.getPayment().getNote() : "") +
                "\n\nDziękujemy za zakupy.";
    }

    private BigDecimal calculateGrossValue(List<CartItem> items, Shipment shipment) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(
                        BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(shipment.getPrice());

    }


    public void saveOrderRows(Cart cart, Long orderId, Shipment shipment){
        saveProductRows(cart, orderId);
        saveShipmentRow(orderId, shipment);

    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(OrderRow.builder()
                        .quantity(1)
                        .price(shipment.getPrice())
                        .shipmentId(shipment.getId())
                        .orderId(orderId)
                .build());
    }

    private void saveProductRows(Cart cart, Long orderId) {
        List<OrderRow> orderRows = cart.getItems().stream()
                .map(cartItem -> OrderRow.builder()
                        .quantity(cartItem.getQuantity())
                        .productId(cartItem.getProduct().getId())
                        .price(cartItem.getProduct().getPrice())
                        .orderId(orderId)
                        .build()
                ).toList();
        orderRowRepository.saveAll(orderRows);
    }
}
