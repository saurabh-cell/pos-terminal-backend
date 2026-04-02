package com.pos.terminal.controller;

import com.pos.terminal.model.Bill;
import com.pos.terminal.service.BillService;
import com.pos.terminal.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "http://localhost:5173")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillRepository billRepository;

    @PostMapping("/generate")
    public Bill generateBill(@RequestBody Bill bill) {
        return billService.createBill(bill);
    }

    @GetMapping("/history")
    public List<Bill> getHistory() {
        return billRepository.findAll();
    }
}