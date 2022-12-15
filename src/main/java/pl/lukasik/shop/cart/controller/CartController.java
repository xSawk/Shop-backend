package pl.lukasik.shop.cart.controller;


import org.springframework.web.bind.annotation.*;
import pl.lukasik.shop.cart.controller.dto.CartSummaryDto;
import pl.lukasik.shop.cart.controller.mapper.CartMapper;
import pl.lukasik.shop.cart.model.dto.CartProductDto;
import pl.lukasik.shop.cart.service.CartService;

import java.util.List;

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

    @PutMapping("/{id}/update")
    public CartSummaryDto updateCart(@PathVariable Long id, @RequestBody List<CartProductDto> cartProductDtoList){
        return CartMapper.mapToCartSummary(cartService.updateCart(id, cartProductDtoList));
    }
}
