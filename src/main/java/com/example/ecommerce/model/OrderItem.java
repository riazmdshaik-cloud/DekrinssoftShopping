// src/main/java/com/example/ecommerce/model/OrderItem.java
package com.example.ecommerce.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Product product;
    
    private Integer quantity;
    private Double subtotal;
    
    // Constructors
    public OrderItem() {}
    
    public OrderItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = product.getPrice().doubleValue() * quantity;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
}
