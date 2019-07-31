package com.trilogyed.Invoice.dao;

import com.trilogyed.Invoice.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {

    @Autowired
    InvoiceDao invoiceDao;
    @Before
    public void SetUp() throws Exception{

        List<Invoice> cList = invoiceDao.getAllInvoices();

        for(Invoice c : cList){
            invoiceDao.deleteInvoice(c.getInvoiceId());
        }

    }

    @Test
    public void addGetDeleteInvoice(){
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2020,12,12));
        invoice = invoiceDao.createInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice1,invoice);

        invoiceDao.deleteInvoice(invoice.getInvoiceId());

        invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertNull(invoice1);

    }

    @Test
    public void getAllInvoices(){

        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2020,12,12));
        invoice = invoiceDao.createInvoice(invoice);

        invoice =new Invoice();
        invoice.setCustomerId(2);
        invoice.setPurchaseDate(LocalDate.of(2021,10,8));
        invoiceDao.createInvoice(invoice);

        List<Invoice> cList = invoiceDao.getAllInvoices();

        assertEquals(cList.size(),2);
    }

    @Test
    public void updateInvoice(){
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2020,12,12));
        invoice = invoiceDao.createInvoice(invoice);

        invoice.setPurchaseDate(LocalDate.of(1997,10,10));
        invoiceDao.updateInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice1,invoice);
    }


}
