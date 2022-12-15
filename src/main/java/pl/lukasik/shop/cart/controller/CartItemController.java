package pl.lukasik.shop.cart.controller;


import org.springframework.web.bind.annotation.*;
import pl.lukasik.shop.cart.service.CartItemService;

@RestController
@RequestMapping("/cartItems")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;


    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id){
            cartItemService.deleteCartItem(id);
    }

    @GetMapping("/count/{cartId}")
    public Long countItemInCart(@PathVariable Long cartId){
        return cartItemService.countItemInCart(cartId);
    }


}
