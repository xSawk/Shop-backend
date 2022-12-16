package pl.lukasik.shop.order.controller;


import org.springframework.web.bind.annotation.*;
import pl.lukasik.shop.order.controller.dto.InitOrder;
import pl.lukasik.shop.order.model.dto.OrderDto;
import pl.lukasik.shop.order.model.dto.OrderSummary;
import pl.lukasik.shop.order.service.OrderService;
import pl.lukasik.shop.order.service.PaymentService;
import pl.lukasik.shop.order.service.ShipmentService;

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
    public OrderSummary placeOrder(@RequestBody OrderDto orderDto){
        return orderService.placeOrder(orderDto);
    }

    @GetMapping("/initData")
    public InitOrder initData(){
        return InitOrder.builder()
                .shipment(shipmentService.getShipments())
                .payment(paymentService.getPayments())
                .build();
    }
}
