package pl.lukasik.shop.order.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lukasik.shop.common.mail.EmailService;
import pl.lukasik.shop.common.model.Cart;
import pl.lukasik.shop.common.repository.CartItemRepository;
import pl.lukasik.shop.common.repository.CartRepository;
import pl.lukasik.shop.order.model.*;
import pl.lukasik.shop.order.model.dto.OrderDto;
import pl.lukasik.shop.order.model.dto.OrderListDto;
import pl.lukasik.shop.order.model.dto.OrderSummary;
import pl.lukasik.shop.order.repository.OrderRepository;
import pl.lukasik.shop.order.repository.OrderRowRepository;
import pl.lukasik.shop.order.repository.PaymentRepository;
import pl.lukasik.shop.order.repository.ShipmentRepository;

import java.util.List;

import static pl.lukasik.shop.order.service.mapper.OrderDtoMapper.mapToOrderListDto;
import static pl.lukasik.shop.order.service.mapper.OrderEmailMessageMapper.emailMsg;
import static pl.lukasik.shop.order.service.mapper.OrderMapper.*;

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
    public OrderSummary placeOrder(OrderDto orderDto, String userName) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();
        Order newOrder = orderRepository.save(createNewOrder(orderDto, cart, shipment, payment, userName));
        saveOrderRows(cart, newOrder.getId(), shipment);
        clearOrderCart(orderDto);
        emailService.send(newOrder.getEmail(),
                "Twoje zamówienie zostało przyjęte",
                emailMsg(newOrder));
        return createOrderSummary(payment, newOrder);

    }

    private void clearOrderCart(OrderDto orderDto) {
        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteCartById(orderDto.getCartId());
    }

    public void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(orderId, shipment);

    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(mapToOrderRow(orderId, shipment));
    }

    private void saveProductRows(Cart cart, Long orderId) {
        List<OrderRow> orderRows = cart.getItems().stream()
                .map(cartItem -> mapToOrderRowWithQuantity(orderId, cartItem)).toList();
        orderRowRepository.saveAll(orderRows);
    }

    public List<OrderListDto> getOrdersForCustomer(String userName) {
        return mapToOrderListDto(orderRepository.findByUserUsername(userName));
    }

}
