package com.pos.terminal.controller;

import com.pos.terminal.model.Sale;
import com.pos.terminal.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "http://localhost:5173")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping("/checkout")
    public Sale checkout(@RequestBody Sale sale) {
        // This triggers the DB save and the Email sending
        return saleService.processSale(sale);
    }
}