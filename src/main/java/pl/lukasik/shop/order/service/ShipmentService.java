package pl.lukasik.shop.order.service;


import org.springframework.stereotype.Service;
import pl.lukasik.shop.order.model.Shipment;
import pl.lukasik.shop.order.repository.ShipmentRepository;

import java.util.List;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public List<Shipment> getShipments() {
        return shipmentRepository.findAll();
    }
}
