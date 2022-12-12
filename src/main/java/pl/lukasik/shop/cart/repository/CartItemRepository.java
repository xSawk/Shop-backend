package pl.lukasik.shop.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukasik.shop.cart.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Long countByCartId(Long cardId);
}
