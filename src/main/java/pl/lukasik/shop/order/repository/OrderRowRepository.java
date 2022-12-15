package pl.lukasik.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukasik.shop.order.model.OrderRow;

public interface OrderRowRepository extends JpaRepository<OrderRow, Long> {
}
