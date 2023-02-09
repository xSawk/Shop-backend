package pl.lukasik.shop.order.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.lukasik.shop.common.mail.EmailService;
import pl.lukasik.shop.common.model.Cart;
import pl.lukasik.shop.common.model.CartItem;
import pl.lukasik.shop.common.model.Product;
import pl.lukasik.shop.common.repository.CartItemRepository;
import pl.lukasik.shop.common.repository.CartRepository;
import pl.lukasik.shop.order.model.OrderStatus;
import pl.lukasik.shop.order.model.Payment;
import pl.lukasik.shop.order.model.PaymentType;
import pl.lukasik.shop.order.model.Shipment;
import pl.lukasik.shop.order.model.dto.OrderDto;
import pl.lukasik.shop.order.model.dto.OrderSummary;
import pl.lukasik.shop.order.repository.OrderRepository;
import pl.lukasik.shop.order.repository.OrderRowRepository;
import pl.lukasik.shop.order.repository.PaymentRepository;
import pl.lukasik.shop.order.repository.ShipmentRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {


    @Mock
    private CartRepository cartRepository;
    @Mock
    private ShipmentRepository shipmentRepository;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderRowRepository orderRowRepository;
    @Mock
    private CartItemRepository cartItemRepository;
    @Mock
    private EmailService emailSender;
    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldPlaceOrder(){
        //given
        OrderDto orderDto = Mocks.createDto();
        when(cartRepository.findById(any())).thenReturn(Mocks.createCart());
        when(shipmentRepository.findById(any())).thenReturn(Mocks.createShipment());
        when(paymentRepository.findById(any())).thenReturn(Mocks.createPayment());
        when(orderRepository.save(any())).thenAnswer(invocation -> invocation.getArguments()[0]);
        String userName = "Marcin";
        //when
        OrderSummary orderSummary = orderService.placeOrder(orderDto,userName);
        //then
        assertThat(orderSummary).isNotNull();
        assertThat(orderSummary.getStatus()).isEqualTo(OrderStatus.New);
        assertThat(orderSummary.getGrossValue()).isEqualTo(new BigDecimal("18.50"));
        assertThat(orderSummary.getPayment().getType()).isEqualTo(PaymentType.TRANSFER_BANK);
        assertThat(orderSummary.getPayment().getName()).isEqualTo("paypal");
        assertThat(orderSummary.getPayment().getId()).isEqualTo(1L);


    }


}