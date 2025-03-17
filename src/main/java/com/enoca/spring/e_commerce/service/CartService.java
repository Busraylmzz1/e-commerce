package com.enoca.spring.e_commerce.service;


import com.enoca.spring.e_commerce.entity.Cart;
import com.enoca.spring.e_commerce.entity.CartItem;
import com.enoca.spring.e_commerce.entity.Product;
import com.enoca.spring.e_commerce.repository.CartRepository;
import com.enoca.spring.e_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;


    public Cart addProductToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("Not enough stock");
        }


        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cart.getCartItems().add(cartItem);
        cart.calculateTotalPrice();
        cartRepository.save(cart);

        return cart;
    }


    public Cart removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getCartItems().removeIf(item -> item.getProduct().getId().equals(productId));  // Sepet öğesini sil

        cart.calculateTotalPrice();
        cartRepository.save(cart);

        return cart;
    }


    public void emptyCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getCartItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
    }
}