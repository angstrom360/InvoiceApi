package com.trilogyed.Invoice.dao;

import com.trilogyed.Invoice.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {



    //-------------- Basic CRUD Methods ------------//

    InvoiceItem createInvoiceItem(InvoiceItem invoiceItem);
    InvoiceItem getInvoiceItem(int id);
    List<InvoiceItem> getAllInvoiceItems();
    void updateInvoiceItem(InvoiceItem invoiceItem);
    void deleteInvoiceItem(int id);

    // -------------------------------------------//
    List<InvoiceItem> getInvoiceItemsByInvoice(int id);


}
