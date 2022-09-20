package com.med_ali.invoice_generator.controllers;

import com.med_ali.invoice_generator.models.Command;
import com.med_ali.invoice_generator.service.InvoiceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class InvoiceGeneratorController {

    private final InvoiceGenerator invoiceGenerator;

    @Autowired
    public InvoiceGeneratorController(InvoiceGenerator invoiceGenerator) {
        this.invoiceGenerator = invoiceGenerator;
    }

    @PostMapping("/invoice/generate")
    public byte[] generateInvoice(@RequestBody Command command) throws IOException {
        byte[] mybytes = new byte[20];
        invoiceGenerator.generatePdf(command).write(mybytes);
        return mybytes;
    }
}
