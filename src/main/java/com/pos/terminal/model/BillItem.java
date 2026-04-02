package com.pos.terminal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BillItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Integer quantity;
    private Double price; // Price at the time of sale
}