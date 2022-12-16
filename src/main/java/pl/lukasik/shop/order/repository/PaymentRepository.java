package pl.lukasik.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukasik.shop.order.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
