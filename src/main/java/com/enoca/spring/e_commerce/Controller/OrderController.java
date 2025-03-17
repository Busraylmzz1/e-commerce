package com.enoca.spring.e_commerce.Controller;

import com.enoca.spring.e_commerce.entity.Order;
import com.enoca.spring.e_commerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{cartId}")
    public Order placeOrder(@PathVariable Long cartId) {
        return orderService.placeOrder(cartId);
    }


    @GetMapping("/{orderId}")
    public Order getOrderForCode(@PathVariable Long orderId) {
        return orderService.getOrderForCode(orderId);
    }


    @GetMapping("/customer/{customerId}")
    public List<Order> getAllOrdersForCustomer(@PathVariable Long customerId) {
        return orderService.getAllOrdersForCustomer(customerId);
    }
}