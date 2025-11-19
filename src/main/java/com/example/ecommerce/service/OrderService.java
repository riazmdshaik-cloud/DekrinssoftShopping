// src/main/java/com/example/ecommerce/service/OrderService.java
package com.example.ecommerce.service;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductService productService;
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
    
    public Order createOrder(Order order) {
        // Calculate total amount
        double total = order.getItems().stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum();
        order.setTotalAmount(total);
        
        return orderRepository.save(order);
    }
    
    public Order addItemToOrder(Long orderId, Long productId, Integer quantity) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        Optional<Product> productOpt = productService.getProductById(productId);
        
        if (orderOpt.isPresent() && productOpt.isPresent()) {
            Order order = orderOpt.get();
            Product product = productOpt.get();
            
            OrderItem orderItem = new OrderItem(product, quantity);
            order.getItems().add(orderItem);
            
            // Recalculate total
            double total = order.getItems().stream()
                    .mapToDouble(OrderItem::getSubtotal)
                    .sum();
            order.setTotalAmount(total);
            
            return orderRepository.save(order);
        }
        return null;
    }
}
