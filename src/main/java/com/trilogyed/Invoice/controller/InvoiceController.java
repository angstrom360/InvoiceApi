package com.trilogyed.Invoice.controller;

import com.sun.jersey.api.NotFoundException;
import com.trilogyed.Invoice.servicelayer.InvoiceServiceLayer;
import com.trilogyed.Invoice.viewmodel.InvoiceItemViewModel;
import com.trilogyed.Invoice.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoiceController {


    /**----------------------------------- REST CONTROLLER ----------------------------------------------------------*/
    @Autowired
    InvoiceServiceLayer service;


    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    public InvoiceViewModel createInvoices(@RequestBody @Valid InvoiceViewModel invoiceViewModel){
        return service.createInvoice(invoiceViewModel);
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    public InvoiceViewModel getInvoices(@PathVariable int id){
        InvoiceViewModel invoiceViewModel = service.getInvoice(id);
        if(invoiceViewModel == null)
            throw new NotFoundException("Invoice could not be found for id "+id);
        return invoiceViewModel;

    }

    @RequestMapping(value = "/invoice/all", method = RequestMethod.GET)
    public List<InvoiceViewModel> getAllInvoice(){
        return service.getAllInvoice();
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.PUT)
    public void updateInvoices(@RequestBody @Valid InvoiceViewModel invoiceViewModel, @PathVariable int id) {
        if(invoiceViewModel.getInvoiceId() == 0)
            invoiceViewModel.setInvoiceId(id);
        if(id != invoiceViewModel.getInvoiceId()) {

            throw new IllegalArgumentException("Invoice ID on path must match the ID in the Invoice Object.");
        }
        service.updateInvoice(invoiceViewModel);
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    public void deleteInvoice(@PathVariable int id) {
        service.deleteInvoice(id);

    }

    /**----------------------------------------------------------------------------------------------------------------*/
    /**----------------------------------- REST CONTROLLER ----------------------------------------------------------*/

    @RequestMapping(value = "/invoiceItem", method = RequestMethod.POST)
    public InvoiceItemViewModel createInvoiceItems(@RequestBody @Valid InvoiceItemViewModel invoiceItemViewModel){
        return service.createInvoiceItem(invoiceItemViewModel);
    }

    @RequestMapping(value = "/invoiceItem/{id}", method = RequestMethod.GET)
    public InvoiceItemViewModel getInvoiceItems(@PathVariable int id){
        InvoiceItemViewModel invoiceItemViewModel = service.getInvoiceItem(id);
        if(invoiceItemViewModel == null)
            throw new NotFoundException("InvoiceItem could not be found for id "+id);
        return invoiceItemViewModel;

    }

    @RequestMapping(value = "/invoiceItem/all", method = RequestMethod.GET)
    public List<InvoiceItemViewModel> getAllInvoiceItem(){
        return service.getAllInvoiceItem();
    }

    @RequestMapping(value ="/invoiceItem/invoice/{id}",method=RequestMethod.GET)
    public List<InvoiceItemViewModel> getInvoiceItemByInvoiceId(@PathVariable @Valid int id){
        return service.getAllInvoiceItemByInvoiceId(id);
    }

    @RequestMapping(value = "/invoiceItem/{id}", method = RequestMethod.PUT)
    public void updateInvoiceItems(@RequestBody @Valid InvoiceItemViewModel invoiceItemViewModel, @PathVariable int id) {
        if(invoiceItemViewModel.getInvoiceItemId() == 0)
            invoiceItemViewModel.setInvoiceItemId(id);
        if(id != invoiceItemViewModel.getInvoiceItemId()) {

            throw new IllegalArgumentException("InvoiceItem ID on path must match the ID in the InvoiceItem Object.");
        }
        service.updateInvoiceItem(invoiceItemViewModel);
    }

    @RequestMapping(value = "/invoiceItem/{id}", method = RequestMethod.DELETE)
    public void deleteInvoiceItem(@PathVariable int id) {
        service.deleteInvoiceItem(id);

    }

    /**----------------------------------------------------------------------------------------------------------------*/








}
