package pl.lukasik.shop.order.controller.dto;


import lombok.Builder;
import lombok.Getter;
import pl.lukasik.shop.order.model.Payment;
import pl.lukasik.shop.order.model.Shipment;

import java.util.List;

@Getter
@Builder
public class InitOrder {

    private List<Shipment> shipment;
    private List<Payment> payment;
}
