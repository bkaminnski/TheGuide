package com.softwarewithpassion.electricityplans.invoices.control;

import com.softwarewithpassion.electricityplans.invoices.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class InvoicesRepository {
    private final Logger logger = Logger.getLogger(InvoicesRepository.class.getName());
    private final ArrayList<Invoice> invoices;

    @Autowired
    public InvoicesRepository(InvoiceGenerator invoiceGenerator) {
        logger.info("generating invoices");
        LocalDateTime sinceClosed = LocalDateTime.parse("2020-01-01T00:00:00");
        LocalDateTime untilOpen = LocalDateTime.parse("2020-01-08T00:00:00");
        invoices = new ArrayList<>();
        invoices.add(invoiceGenerator.generateInvoice(sinceClosed, untilOpen, 4512, new WeekendPlan()));
        invoices.add(invoiceGenerator.generateInvoice(sinceClosed, untilOpen, 4513, new NightPlan()));
        invoices.add(invoiceGenerator.generateInvoice(sinceClosed, untilOpen, 4514, new NightPlan()));
    }

    public List<Invoice> findAll() {
        return invoices;
    }
}
