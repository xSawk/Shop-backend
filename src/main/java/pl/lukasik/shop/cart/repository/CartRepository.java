package pl.lukasik.shop.cart.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukasik.shop.cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
