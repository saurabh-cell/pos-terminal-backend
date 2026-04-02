package com.pos.terminal.model;

import jakarta.persistence.*; // Important!
import lombok.Data;

@Entity // This tells Spring to create a table
@Table(name = "users")
@Data
public class User {
    @Id // Every table needs a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String shopName;
}