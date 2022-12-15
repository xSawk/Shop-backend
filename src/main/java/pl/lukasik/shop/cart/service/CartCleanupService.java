package pl.lukasik.shop.cart.service;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lukasik.shop.common.model.Cart;
import pl.lukasik.shop.common.repository.CartItemRepository;
import pl.lukasik.shop.common.repository.CartRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartCleanupService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartCleanupService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Transactional
    @Scheduled(cron = "0 0 4 * * *")
    public void cleanupOldCards(){

        List<Cart> carts = cartRepository.findByCreatedLessThan(LocalDateTime.now().minusDays(3));
        carts.forEach(cart -> {
            cartItemRepository.deleteByCartId(cart.getId());
            cartRepository.deleteCartById(cart.getId());
        });




    }
}
