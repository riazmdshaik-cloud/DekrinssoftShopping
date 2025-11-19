// src/main/java/com/example/ecommerce/controller/OrderController.java
package com.example.ecommerce.controller;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("order", new Order());
        return "orders";
    }
    
    @PostMapping
    public String createOrder(@ModelAttribute Order order) {
        orderService.createOrder(order);
        return "redirect:/orders";
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Order> getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
}
