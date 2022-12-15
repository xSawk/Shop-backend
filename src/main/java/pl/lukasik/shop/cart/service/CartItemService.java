package pl.lukasik.shop.cart.service;


import org.springframework.stereotype.Service;
import pl.lukasik.shop.common.repository.CartItemRepository;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }


    public Long countItemInCart(Long cardId){
        return cartItemRepository.countByCartId(cardId);
    }
}
