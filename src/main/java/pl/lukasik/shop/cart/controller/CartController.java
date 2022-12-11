package pl.lukasik.shop.cart.controller;


import org.springframework.web.bind.annotation.*;
import pl.lukasik.shop.cart.controller.dto.CartSummaryDto;
import pl.lukasik.shop.cart.controller.mapper.CartMapper;
import pl.lukasik.shop.cart.model.CartProductDto;
import pl.lukasik.shop.cart.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public CartSummaryDto getCart(@PathVariable Long id){
        return CartMapper.mapToCartSummary(cartService.getCart(id));
    }

    @PutMapping("/{id}")
    public CartSummaryDto addProductToCart(@PathVariable Long id, @RequestBody CartProductDto cartProductDto){
        return CartMapper.mapToCartSummary(cartService.addProductToCart(id, cartProductDto));
    }
}
