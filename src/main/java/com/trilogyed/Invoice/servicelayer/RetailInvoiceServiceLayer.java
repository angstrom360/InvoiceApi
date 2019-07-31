package com.trilogyed.Invoice.servicelayer;


import com.trilogyed.Invoice.dao.InvoiceDao;
import com.trilogyed.Invoice.dao.InvoiceItemDao;
import com.trilogyed.Invoice.model.Invoice;
import com.trilogyed.Invoice.model.InvoiceItem;
import com.trilogyed.Invoice.viewmodel.RetailInvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RefreshScope
public class RetailInvoiceServiceLayer {

    @Autowired
    InvoiceItemDao invoiceItemDao;

    @Autowired
    InvoiceDao invoiceDao;

    @Autowired
    public RetailInvoiceServiceLayer(InvoiceItemDao invoiceItemDao,InvoiceDao invoiceDao) {

        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
    }

    private RetailInvoiceViewModel buildRetailInvoiceViewModel (Invoice invoice){
        RetailInvoiceViewModel retailInvoiceViewModel = new RetailInvoiceViewModel();
        retailInvoiceViewModel.setInvoiceId(invoice.getInvoiceId());
        retailInvoiceViewModel.setCustomerId(invoice.getCustomerId());
        retailInvoiceViewModel.setPurchaseDate(invoice.getPurchaseDate());

        List<InvoiceItem> invoiceItems = invoiceItemDao.getInvoiceItemsByInvoice(invoice.getInvoiceId());
        retailInvoiceViewModel.setInvoiceItems(invoiceItems);

        return retailInvoiceViewModel;
    }


    @Transactional
    public RetailInvoiceViewModel createRetailInvoice(RetailInvoiceViewModel retailInvoiceViewModel){
        Invoice invoice = new Invoice();
        invoice.setCustomerId(retailInvoiceViewModel.getCustomerId());
        invoice.setPurchaseDate(retailInvoiceViewModel.getPurchaseDate());
        invoice =invoiceDao.createInvoice(invoice);
        retailInvoiceViewModel.setInvoiceId(invoice.getInvoiceId());

        List<InvoiceItem> invoiceItems = retailInvoiceViewModel.getInvoiceItems();
        invoiceItems.stream()
                .forEach(i ->
                {
                    i.setInvoiceId(retailInvoiceViewModel.getInvoiceId());
                    invoiceItemDao.createInvoiceItem(i);
                });

        return retailInvoiceViewModel;
    }

    public RetailInvoiceViewModel getRetailInvoice(int id){
        Invoice invoice = invoiceDao.getInvoice(id);
        if(invoice == null)
            return null;
        else
            return buildRetailInvoiceViewModel(invoice);

    }

    public List<RetailInvoiceViewModel> getAllRetailInvoice(){
        List<Invoice> retailInvoiceList = invoiceDao.getAllInvoices();
        List<RetailInvoiceViewModel> retailInvoiceViewModelList = new ArrayList<>();

        for(Invoice invoice : retailInvoiceList){
            RetailInvoiceViewModel retailInvoiceViewModel = buildRetailInvoiceViewModel(invoice);
            retailInvoiceViewModelList.add(retailInvoiceViewModel);
        }
        return retailInvoiceViewModelList;
    }

    public List<RetailInvoiceViewModel> getRetailInvoiceByCustomerId(int customerId){
        List<Invoice> retailInvoiceList = invoiceDao.getInvoiceByCustomerId(customerId);
        List<RetailInvoiceViewModel> retailInvoiceViewModelList = new ArrayList<>();

        for(Invoice invoice: retailInvoiceList){
            RetailInvoiceViewModel retailInvoiceViewModel = buildRetailInvoiceViewModel(invoice);
            retailInvoiceViewModelList.add(retailInvoiceViewModel);
        }
        return retailInvoiceViewModelList;
    }


    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem){
        return invoiceItemDao.createInvoiceItem(invoiceItem);
    }

    public InvoiceItem getInvoiceItem(int id){
        return invoiceItemDao.getInvoiceItem(id);
    }

    public List<InvoiceItem> getAllInvoiceItem(){
        return invoiceItemDao.getAllInvoiceItems();
    }
}
