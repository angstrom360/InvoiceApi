package com.trilogyed.Invoice.dao;

import com.trilogyed.Invoice.model.Invoice;

import java.util.List;

public interface InvoiceDao {


    //-------------- Basic CRUD Methods ------------//

    Invoice createInvoice(Invoice invoice);
    Invoice getInvoice(int id);
    List<Invoice> getAllInvoices();
    void updateInvoice(Invoice invoice);
    void deleteInvoice(int id);

    // -------------------------------------------//
    List<Invoice> getInvoiceByCustomerId(int id);

}
