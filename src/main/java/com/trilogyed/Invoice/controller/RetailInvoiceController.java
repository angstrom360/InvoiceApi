package com.trilogyed.Invoice.controller;

import com.sun.jersey.api.NotFoundException;
import com.trilogyed.Invoice.servicelayer.RetailInvoiceServiceLayer;
import com.trilogyed.Invoice.viewmodel.RetailInvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RetailInvoiceController {

    @Autowired
    RetailInvoiceServiceLayer service;

    @RequestMapping(value ="/retailInvoice",method= RequestMethod.POST)
    public RetailInvoiceViewModel createInvoice(@RequestBody @Valid RetailInvoiceViewModel retailInvoiceViewModel){
        return service.createRetailInvoice(retailInvoiceViewModel);
    }

    @RequestMapping(value ="/retailInvoice/{id}", method= RequestMethod.GET)
    public RetailInvoiceViewModel getInvoices(@PathVariable int id){
        RetailInvoiceViewModel retailInvoiceViewModel = service.getRetailInvoice(id);
        if(retailInvoiceViewModel == null)
            throw new NotFoundException("Invoice could not be found for id "+id);
        return retailInvoiceViewModel;
    }

    @RequestMapping(value = "/retailInvoice/all", method = RequestMethod.GET)
    public List<RetailInvoiceViewModel> getAllInvoices(){
        return service.getAllRetailInvoice();
    }

    @RequestMapping(value="/retailInvoice/customer/{id}",method=RequestMethod.GET)
    public List<RetailInvoiceViewModel> getInvoiceByCustomerId(@PathVariable @Valid int id){
        return service.getRetailInvoiceByCustomerId(id);
    }

}
