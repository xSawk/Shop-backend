package pl.lukasik.shop.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.lukasik.shop.common.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Long countByCartId(Long cardId);


    @Query("delete from CartItem ci where ci.cartId=:cartId")
    @Modifying
    void deleteByCartId(Long cartId);


}
