package com.trilogyed.Invoice.servicelayer;

import com.trilogyed.Invoice.dao.InvoiceDao;
import com.trilogyed.Invoice.dao.InvoiceItemDao;
import com.trilogyed.Invoice.model.Invoice;
import com.trilogyed.Invoice.model.InvoiceItem;
import com.trilogyed.Invoice.viewmodel.InvoiceItemViewModel;
import com.trilogyed.Invoice.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RefreshScope
public class InvoiceServiceLayer {

    @Autowired
    InvoiceDao invoiceDao;

    @Autowired
    InvoiceItemDao invoiceItemDao;


    @Autowired
    public InvoiceServiceLayer(InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao) {
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
    }


    public InvoiceServiceLayer(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    private InvoiceViewModel buildInvoiceViewModel (Invoice invoice){
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setInvoiceId(invoice.getInvoiceId());
        invoiceViewModel.setCustomerId(invoice.getCustomerId());
        invoiceViewModel.setPurchaseDate(invoice.getPurchaseDate());

        return invoiceViewModel;
    }

    private InvoiceItemViewModel buildInvoiceItemViewModel (InvoiceItem invoiceItem){
        InvoiceItemViewModel invoiceItemViewModel = new InvoiceItemViewModel();
        invoiceItemViewModel.setInvoiceItemId(invoiceItem.getInvoiceItemId());
        invoiceItemViewModel.setInvoiceId(invoiceItem.getInvoiceId());
        invoiceItemViewModel.setInventoryId(invoiceItem.getInventoryId());
        invoiceItemViewModel.setQuantity(invoiceItem.getQuantity());
        invoiceItemViewModel.setUnitPrice(invoiceItem.getUnitPrice());

        return invoiceItemViewModel;
    }


    /**---------------------------------------------------------------------------------------------------------------*/

    /**INVOICE CRUD OPERATION */
    @Transactional
    public InvoiceViewModel createInvoice(InvoiceViewModel invoiceViewModel){

        Invoice invoice = new Invoice();
        invoice.setCustomerId(invoiceViewModel.getCustomerId());
        invoice.setPurchaseDate(invoiceViewModel.getPurchaseDate());
        invoice = invoiceDao.createInvoice(invoice);
        invoiceViewModel.setInvoiceId(invoice.getInvoiceId());

        return invoiceViewModel;
    }

    public InvoiceViewModel getInvoice(int id){
        Invoice invoice = invoiceDao.getInvoice(id);
        if(invoice == null)
            return null;
        else
            return buildInvoiceViewModel(invoice);

    }

    public List<InvoiceViewModel> getAllInvoice(){
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        List<InvoiceViewModel> invoiceViewModelList = new ArrayList<>();

        for(Invoice cList : invoiceList){
            InvoiceViewModel invoiceViewModel = buildInvoiceViewModel(cList);
            invoiceViewModelList.add(invoiceViewModel);
        }

        return invoiceViewModelList;
    }

    public void updateInvoice(InvoiceViewModel invoiceViewModel){
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceViewModel.getInvoiceId());
        invoice.setCustomerId(invoiceViewModel.getCustomerId());
        invoice.setPurchaseDate(invoiceViewModel.getPurchaseDate());
        invoiceDao.updateInvoice(invoice);
    }

    public void deleteInvoice(int id){
        invoiceDao.deleteInvoice(id);
    }

    /**INVOICE_ITEM CRUD OPERATION*/
    @Transactional
    public InvoiceItemViewModel createInvoiceItem(InvoiceItemViewModel invoiceItemViewModel){
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoiceItemViewModel.getInvoiceId());
        invoiceItem.setInventoryId(invoiceItemViewModel.getInventoryId());
        invoiceItem.setQuantity(invoiceItemViewModel.getQuantity());
        invoiceItem.setUnitPrice(invoiceItemViewModel.getUnitPrice());
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);
        invoiceItemViewModel.setInvoiceItemId(invoiceItem.getInvoiceItemId());

        return invoiceItemViewModel;
    }

    public InvoiceItemViewModel getInvoiceItem(int id){
        InvoiceItem invoiceItem = invoiceItemDao.getInvoiceItem(id);
        if(invoiceItem == null)
            return null;
        else
            return buildInvoiceItemViewModel(invoiceItem);

    }
    public List<InvoiceItemViewModel> getAllInvoiceItem(){
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItems();
        List<InvoiceItemViewModel> invoiceItemViewModelList = new ArrayList<>();

        for(InvoiceItem cList : invoiceItemList){
            InvoiceItemViewModel invoiceItemViewModel = buildInvoiceItemViewModel(cList);
            invoiceItemViewModelList.add(invoiceItemViewModel);
        }

        return invoiceItemViewModelList;
    }

    public List<InvoiceItemViewModel> getAllInvoiceItemByInvoiceId(int id){
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getInvoiceItemsByInvoice(id);
        List<InvoiceItemViewModel> invoiceItemViewModelList = new ArrayList<>();

        for(InvoiceItem cList : invoiceItemList){
            InvoiceItemViewModel invoiceItemViewModel = buildInvoiceItemViewModel(cList);
            invoiceItemViewModelList.add(invoiceItemViewModel);
        }

        return invoiceItemViewModelList;
    }

    public void updateInvoiceItem(InvoiceItemViewModel invoiceItemViewModel){
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(invoiceItemViewModel.getInvoiceItemId());
        invoiceItem.setInvoiceId(invoiceItemViewModel.getInvoiceId());
        invoiceItem.setInventoryId(invoiceItemViewModel.getInventoryId());
        invoiceItem.setQuantity(invoiceItemViewModel.getQuantity());
        invoiceItem.setUnitPrice(invoiceItemViewModel.getUnitPrice());
        invoiceItemDao.updateInvoiceItem(invoiceItem);

    }
    public void deleteInvoiceItem(int id){
        invoiceItemDao.deleteInvoiceItem(id);
    }




}
