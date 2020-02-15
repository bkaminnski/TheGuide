package com.softwarewithpassion.electricityplansimproved.invoices.control;

import com.softwarewithpassion.electricityplansimproved.invoices.entity.InvoiceLine;

import java.util.List;

public interface InvoiceablePlan {
    List<InvoiceLine> toInvoiceLines();
}
