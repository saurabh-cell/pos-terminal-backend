package com.pos.terminal.repository;

import com.pos.terminal.model.Bill; // Import your Bill model
import org.springframework.data.jpa.repository.JpaRepository; // Import the JPA tool
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    // JpaRepository already provides save(), findAll(), findById()
    // You don't need to write any code here for basic functionality!
}