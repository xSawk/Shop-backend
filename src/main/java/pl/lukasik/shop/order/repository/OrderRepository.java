package pl.lukasik.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukasik.shop.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
