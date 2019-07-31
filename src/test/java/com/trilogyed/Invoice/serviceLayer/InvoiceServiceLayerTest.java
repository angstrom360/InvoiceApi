package com.trilogyed.Invoice.serviceLayer;

import com.trilogyed.Invoice.dao.InvoiceDao;
import com.trilogyed.Invoice.dao.InvoiceDaoJdbcTemplate;
import com.trilogyed.Invoice.dao.InvoiceItemDao;
import com.trilogyed.Invoice.dao.InvoiceItemDaoJdbcTemplate;
import com.trilogyed.Invoice.model.Invoice;
import com.trilogyed.Invoice.model.InvoiceItem;
import com.trilogyed.Invoice.servicelayer.InvoiceServiceLayer;
import com.trilogyed.Invoice.viewmodel.InvoiceItemViewModel;
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

public class InvoiceServiceLayerTest {

    @Autowired
    InvoiceDao invoiceDao;

    @Autowired
    InvoiceItemDao invoiceItemDao;

    @Autowired
    InvoiceServiceLayer invoiceServiceLayer;

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

    }
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

    @Before
    public void setUp()throws Exception{
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();
        invoiceServiceLayer = new InvoiceServiceLayer(invoiceDao,invoiceItemDao);
    }

    @Test
    public void createInvoice(){
        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019,1,5));
        invoice = invoiceServiceLayer.createInvoice(invoice);

        InvoiceViewModel invoice1 = new InvoiceViewModel();
        invoice1.setInvoiceId(1);
        invoice1.setCustomerId(1);
        invoice1.setPurchaseDate(LocalDate.of(2019,1,5));

        InvoiceViewModel fromService = invoiceServiceLayer.createInvoice(invoice1);
        assertEquals(invoice,fromService);

    }

    @Test
    public void getInvoice(){

        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019,1,5));
        invoice = invoiceServiceLayer.createInvoice(invoice);

        InvoiceViewModel fromService = invoiceServiceLayer.getInvoice(1);
        assertEquals(invoice,fromService);

    }

    @Test
    public void getAllInvoice(){

        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019,1,5));
        invoice = invoiceServiceLayer.createInvoice(invoice);

        List<InvoiceViewModel> fromService = invoiceServiceLayer.getAllInvoice();
        assertEquals(1,fromService.size());
        assertEquals(invoice,fromService.get(0));

    }

    @Test
    public void createInvoiceItem(){
        InvoiceItemViewModel invoiceItem = new InvoiceItemViewModel();
        invoiceItem.setInvoiceItemId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(100);
        invoiceItem.setUnitPrice(new BigDecimal(250.00));
        invoiceItem = invoiceServiceLayer.createInvoiceItem(invoiceItem);

        InvoiceItemViewModel invoiceItem1 = new InvoiceItemViewModel();
        invoiceItem1.setInvoiceItemId(1);
        invoiceItem1.setInvoiceId(1);
        invoiceItem1.setInventoryId(1);
        invoiceItem1.setQuantity(100);
        invoiceItem1.setUnitPrice(new BigDecimal(250.00));

        InvoiceItemViewModel fromService = invoiceServiceLayer.createInvoiceItem(invoiceItem1);
        assertEquals(invoiceItem,fromService);

    }

    @Test
    public void getInvoiceItem(){

        InvoiceItemViewModel invoiceItem = new InvoiceItemViewModel();
        invoiceItem.setInvoiceItemId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(100);
        invoiceItem.setUnitPrice(new BigDecimal(250.00));
        invoiceItem = invoiceServiceLayer.createInvoiceItem(invoiceItem);

        InvoiceItemViewModel fromService = invoiceServiceLayer.getInvoiceItem(1);
        assertEquals(invoiceItem,fromService);

    }

    @Test
    public void getAllInvoiceItem(){

        InvoiceItemViewModel invoiceItem = new InvoiceItemViewModel();
        invoiceItem.setInvoiceItemId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(100);
        invoiceItem.setUnitPrice(new BigDecimal(250.00));
        invoiceItem = invoiceServiceLayer.createInvoiceItem(invoiceItem);

        List<InvoiceItemViewModel> fromService = invoiceServiceLayer.getAllInvoiceItem();
        assertEquals(1,fromService.size());
        assertEquals(invoiceItem,fromService.get(0));

    }

    @Test
    public void getAllInvoiceItemByInvoiceId(){

        InvoiceItemViewModel invoiceItem = new InvoiceItemViewModel();
        invoiceItem.setInvoiceItemId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(100);
        invoiceItem.setUnitPrice(new BigDecimal(250.00));
        invoiceItem = invoiceServiceLayer.createInvoiceItem(invoiceItem);

        List<InvoiceItemViewModel> fromService = invoiceServiceLayer.getAllInvoiceItemByInvoiceId(1);
        assertEquals(1,fromService.size());
        assertEquals(invoiceItem,fromService.get(0));

    }


}
