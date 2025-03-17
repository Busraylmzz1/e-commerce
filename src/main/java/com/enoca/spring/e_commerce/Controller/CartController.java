package com.enoca.spring.e_commerce.Controller;

import com.enoca.spring.e_commerce.entity.Cart;
import com.enoca.spring.e_commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/{cartId}/add/{productId}")
    public Cart addProductToCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int quantity) {
        return cartService.addProductToCart(cartId, productId, quantity);
    }


    @DeleteMapping("/{cartId}/remove/{productId}")
    public Cart removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        return cartService.removeProductFromCart(cartId, productId);
    }

    // Sepeti boşaltma
    @DeleteMapping("/{cartId}/empty")
    public void emptyCart(@PathVariable Long cartId) {
        cartService.emptyCart(cartId);
    }
}