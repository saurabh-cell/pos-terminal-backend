package com.pos.terminal.service;

import com.pos.terminal.model.Bill;
import com.pos.terminal.model.BillItem;
import com.pos.terminal.model.Product;
import com.pos.terminal.repository.BillRepository;
import com.pos.terminal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional // This ensures if one step fails, the whole bill is cancelled (safety first!)
    public Bill createBill(Bill bill) {
        double total = 0;

        for (BillItem item : bill.getItems()) {
            // 1. Find the product in the database
            Product product = productRepository.findByName(item.getProductName())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + item.getProductName()));

            // 2. Check stock
            if (product.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Not enough stock for " + product.getName());
            }

            // 3. Update the stock (Inventory Management)
            product.setQuantity(product.getQuantity() - item.getQuantity());
            productRepository.save(product);

            // 4. Calculate total for this item
            total += item.getPrice() * item.getQuantity();
        }

        bill.setTotalAmount(total);
        return billRepository.save(bill);
    }
}