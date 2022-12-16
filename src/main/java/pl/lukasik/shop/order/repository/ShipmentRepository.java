package pl.lukasik.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukasik.shop.order.model.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
