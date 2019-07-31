package com.trilogyed.Invoice.serviceLayer;

import com.trilogyed.Invoice.dao.InvoiceDao;
import com.trilogyed.Invoice.dao.InvoiceDaoJdbcTemplate;
import com.trilogyed.Invoice.dao.InvoiceItemDao;
import com.trilogyed.Invoice.dao.InvoiceItemDaoJdbcTemplate;
import com.trilogyed.Invoice.model.Invoice;
import com.trilogyed.Invoice.model.InvoiceItem;
import com.trilogyed.Invoice.servicelayer.RetailInvoiceServiceLayer;
import com.trilogyed.Invoice.viewmodel.RetailInvoiceViewModel;
import com.trilogyed.Invoice.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class RetailInvoiceServiceLayerTest {


    InvoiceDao invoiceDao;


    InvoiceItemDao invoiceItemDao;


    @Autowired
    RetailInvoiceServiceLayer retailInvoiceServiceLayer;

    private void setUpInvoiceItemDaoMock(){
        invoiceItemDao = mock(InvoiceItemDaoJdbcTemplate.class);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(100);
        invoiceItem.setUnitPrice(new BigDecimal(250.00));

        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem1.setInvoiceId(1);
        invoiceItem1.setInventoryId(1);
        invoiceItem1.setQuantity(100);
        invoiceItem1.setUnitPrice(new BigDecimal(250.00));

        List<InvoiceItem> cList = new ArrayList<>();
        cList.add(invoiceItem);

        doReturn(invoiceItem).when(invoiceItemDao).createInvoiceItem(invoiceItem1);
        doReturn(invoiceItem).when(invoiceItemDao).getInvoiceItem(1);
        doReturn(cList).when(invoiceItemDao).getAllInvoiceItems();
        doReturn(cList).when(invoiceItemDao).getInvoiceItemsByInvoice(1);

    }
    private void setUpInvoiceDaoMock(){
        invoiceDao = mock(InvoiceDaoJdbcTemplate.class);

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019,1,5));

        Invoice invoice1 = new Invoice();
        invoice1.setCustomerId(1);
        invoice1.setPurchaseDate(LocalDate.of(2019,1,5));

        List<Invoice> cList = new ArrayList<>();
        cList.add(invoice);

        doReturn(invoice).when(invoiceDao).createInvoice(invoice1);
        doReturn(invoice).when(invoiceDao).getInvoice(1);
        doReturn(cList).when(invoiceDao).getAllInvoices();
        doReturn(cList).when(invoiceDao).getInvoiceByCustomerId(1);

    }

    @Before
    public void setUp()throws Exception{
        setUpInvoiceItemDaoMock();
        setUpInvoiceDaoMock();
        retailInvoiceServiceLayer = new RetailInvoiceServiceLayer(invoiceItemDao,invoiceDao);
    }

    @Test
    public void createRetailInvoice(){
        RetailInvoiceViewModel retailInvoiceViewModel = new RetailInvoiceViewModel();
        retailInvoiceViewModel.setInvoiceId(1);
        retailInvoiceViewModel.setCustomerId(1);
        retailInvoiceViewModel.setPurchaseDate(LocalDate.of(2019,1,5));

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(100);
        invoiceItem.setUnitPrice(new BigDecimal(250.00));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        List<InvoiceItem> iList = invoiceItemDao.getInvoiceItemsByInvoice(invoiceItem.getInvoiceItemId());
        retailInvoiceViewModel.setInvoiceItems(iList);

        retailInvoiceViewModel = retailInvoiceServiceLayer.createRetailInvoice(retailInvoiceViewModel);


        RetailInvoiceViewModel fromService = retailInvoiceServiceLayer.getRetailInvoice(retailInvoiceViewModel.getInvoiceId());
        assertEquals(retailInvoiceViewModel,fromService);

    }

    @Test
    public void getAllRetailInvoice(){

        RetailInvoiceViewModel retailInvoiceViewModel = new RetailInvoiceViewModel();
        retailInvoiceViewModel.setInvoiceId(1);
        retailInvoiceViewModel.setCustomerId(1);
        retailInvoiceViewModel.setPurchaseDate(LocalDate.of(2019,1,5));

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(100);
        invoiceItem.setUnitPrice(new BigDecimal(250.00));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);


        List<InvoiceItem> iList = invoiceItemDao.getInvoiceItemsByInvoice(invoiceItem.getInvoiceItemId());
        retailInvoiceViewModel.setInvoiceItems(iList);

        retailInvoiceViewModel = retailInvoiceServiceLayer.createRetailInvoice(retailInvoiceViewModel);

        List<RetailInvoiceViewModel> retailInvoices = retailInvoiceServiceLayer.getAllRetailInvoice();
        assertEquals(1,retailInvoices.size());
        assertEquals(retailInvoiceViewModel,retailInvoices.get(0));

    }

    @Test
    public void getRetailInvoiceByCustomerId(){

        RetailInvoiceViewModel retailInvoiceViewModel = new RetailInvoiceViewModel();
        retailInvoiceViewModel.setInvoiceId(1);
        retailInvoiceViewModel.setCustomerId(1);
        retailInvoiceViewModel.setPurchaseDate(LocalDate.of(2019,1,5));

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(100);
        invoiceItem.setUnitPrice(new BigDecimal(250.00));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);


        List<InvoiceItem> iList = invoiceItemDao.getInvoiceItemsByInvoice(invoiceItem.getInvoiceItemId());
        retailInvoiceViewModel.setInvoiceItems(iList);

        retailInvoiceViewModel = retailInvoiceServiceLayer.createRetailInvoice(retailInvoiceViewModel);

        List<RetailInvoiceViewModel> retailInvoices = retailInvoiceServiceLayer.getRetailInvoiceByCustomerId(retailInvoiceViewModel.getCustomerId());
        assertEquals(1,retailInvoices.size());
        assertEquals(retailInvoiceViewModel,retailInvoices.get(0));

    }


}
