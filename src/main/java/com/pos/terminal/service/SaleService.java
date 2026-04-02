package com.pos.terminal.service;

import com.pos.terminal.model.Sale;
import com.pos.terminal.model.SaleItem;
import com.pos.terminal.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDateTime;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private JavaMailSender mailSender;

    public Sale processSale(Sale sale) {
        sale.setTransactionTime(LocalDateTime.now());

        // Save to DB
        Sale savedSale = saleRepository.save(sale);

        // Send E-Bill
        sendEmailBill(savedSale);

        return savedSale;
    }

    private void sendEmailBill(Sale sale) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(sale.getCustomerEmail());
            helper.setSubject("Your E-Receipt from POS Terminal");

            StringBuilder content = new StringBuilder();
            content.append("<h1>Thank you for your purchase!</h1>");
            content.append("<table border='1'><tr><th>Item</th><th>Qty</th><th>Price</th></tr>");

            for (SaleItem item : sale.getItems()) {
                content.append(String.format("<tr><td>%s</td><td>%d</td><td>%.2f</td></tr>",
                        item.getProductName(), item.getQuantity(), item.getUnitPrice()));
            }

            content.append("</table>");
            content.append("<h3>Total Amount: ₹").append(sale.getTotalAmount()).append("</h3>");

            helper.setText(content.toString(), true); // 'true' means it's HTML
            mailSender.send(message);

        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}