package pl.lukasik.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukasik.shop.order.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserUsername(String userName);
}
