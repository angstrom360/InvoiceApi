package com.trilogyed.Invoice.dao;

import com.trilogyed.Invoice.model.Invoice;
import com.trilogyed.Invoice.model.InvoiceItem;
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
public class InvoiceItemDaoTest {

    @Autowired
    InvoiceItemDao invoiceItemDao;

    @Autowired
    InvoiceDao invoiceDao;

    @Before
    public void SetUp() throws Exception{


        List<InvoiceItem> cList = invoiceItemDao.getAllInvoiceItems();

        for(InvoiceItem c : cList){
            invoiceItemDao.deleteInvoiceItem(c.getInvoiceItemId());
        }
        List<Invoice> bList = invoiceDao.getAllInvoices();

        for(Invoice b : bList){
            invoiceDao.deleteInvoice(b.getInvoiceId());
        }



    }

    @Test
    public void addGetDeleteInvoiceItem(){

        Invoice invoice = invoiceDao.createInvoice(new Invoice(1,1,LocalDate.of(1885,4,4)));


        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(13);
        invoiceItem.setUnitPrice(new BigDecimal("250.00"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());

        assertEquals(invoiceItem1,invoiceItem);

        invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId());

        invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());

        assertNull(invoiceItem1);

    }

    @Test
    public void getAllInvoiceItems(){

        Invoice invoice = invoiceDao.createInvoice(new Invoice(1,1,LocalDate.of(1885,4,4)));



        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(13);
        invoiceItem.setUnitPrice(new BigDecimal("250.00"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        Invoice invoice2 = invoiceDao.createInvoice(new Invoice(1,2,LocalDate.of(1885,4,4)));


        invoiceItem =new InvoiceItem();
        invoiceItem.setInvoiceId(invoice2.getInvoiceId());
        invoiceItem.setInventoryId(2);
        invoiceItem.setQuantity(25);
        invoiceItem.setUnitPrice(new BigDecimal("1000"));
        invoiceItemDao.createInvoiceItem(invoiceItem);

        List<InvoiceItem> cList = invoiceItemDao.getAllInvoiceItems();

        assertEquals(cList.size(),2);
    }

    @Test
    public void updateInvoiceItem(){

        Invoice invoice = invoiceDao.createInvoice(new Invoice(1,1,LocalDate.of(1885,4,4)));


        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(13);
        invoiceItem.setUnitPrice(new BigDecimal("250.00"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        invoiceItem.setUnitPrice(new BigDecimal("50.00"));
        invoiceItemDao.updateInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());

        assertEquals(invoiceItem1,invoiceItem);
    }

}
