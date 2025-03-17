package com.enoca.spring.e_commerce.service;


import com.enoca.spring.e_commerce.entity.Cart;
import com.enoca.spring.e_commerce.entity.CartItem;
import com.enoca.spring.e_commerce.entity.Order;
import com.enoca.spring.e_commerce.entity.OrderItem;
import com.enoca.spring.e_commerce.repository.CartRepository;
import com.enoca.spring.e_commerce.repository.OrderItemRepository;
import com.enoca.spring.e_commerce.repository.OrderRepository;
import com.enoca.spring.e_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    public Order placeOrder(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Order order = new Order();
        order.setCustomer(cart.getCustomer());
        order.setTotalPrice(cart.getTotalPrice());


        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtTimeOfPurchase(cartItem.getProduct().getPrice());
            order.getOrderItems().add(orderItem);
            orderItemRepository.save(orderItem);
        }


        cart.getCartItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);

        return order;
    }


    public Order getOrderForCode(Long orderId) {
        return (Order) orderRepository.findById(orderId).orElse(null);
    }

    public List<Order> getAllOrdersForCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
