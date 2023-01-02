package pl.lukasik.shop.order.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.lukasik.shop.order.controller.dto.InitOrder;
import pl.lukasik.shop.order.model.Order;
import pl.lukasik.shop.order.model.dto.OrderDto;
import pl.lukasik.shop.order.model.dto.OrderListDto;
import pl.lukasik.shop.order.model.dto.OrderSummary;
import pl.lukasik.shop.order.service.OrderService;
import pl.lukasik.shop.order.service.PaymentService;
import pl.lukasik.shop.order.service.ShipmentService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final ShipmentService shipmentService;
    private final PaymentService paymentService;

    public OrderController(OrderService orderService, ShipmentService shipmentService, PaymentService paymentService) {
        this.orderService = orderService;
        this.shipmentService = shipmentService;
        this.paymentService = paymentService;
    }


    @PostMapping
    public OrderSummary placeOrder(@RequestBody OrderDto orderDto,@AuthenticationPrincipal String userName){
        return orderService.placeOrder(orderDto,userName);
    }

    @GetMapping("/initData")
    public InitOrder initData(){
        return InitOrder.builder()
                .shipment(shipmentService.getShipments())
                .payment(paymentService.getPayments())
                .build();
    }

    @GetMapping()
    public List<OrderListDto> getOrders(@AuthenticationPrincipal String userName){
        return orderService.getOrdersForCustomer(userName);

    }
}
