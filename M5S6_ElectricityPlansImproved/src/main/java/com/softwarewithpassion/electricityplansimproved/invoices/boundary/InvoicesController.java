package com.softwarewithpassion.electricityplansimproved.invoices.boundary;

import com.softwarewithpassion.electricityplansimproved.invoices.control.InvoicesRepository;
import com.softwarewithpassion.electricityplansimproved.invoices.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {
    private final InvoicesRepository invoicesRepository;

    @Autowired
    public InvoicesController(InvoicesRepository invoicesRepository) {
        this.invoicesRepository = invoicesRepository;
    }

    @GetMapping
    public List<Invoice> getInvoices() {
        return invoicesRepository.findAll();
    }
}
