package com.pos.terminal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sale_items")
@Data
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;
}