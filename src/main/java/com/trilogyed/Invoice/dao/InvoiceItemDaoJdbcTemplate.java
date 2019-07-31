package com.trilogyed.Invoice.dao;

import com.trilogyed.Invoice.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceItemDaoJdbcTemplate implements InvoiceItemDao {


    //-------------------------Prepared Statements (CRUD QUERIES) ---------------------//

    private static final String INSERT_INVOICE_ITEM_SQL =
            "insert into invoice_item (invoice_id,inventory_id,quantity,unit_price) values (?,?,?,?)";

    private static final String SELECT_INVOICE_ITEM_SQL =
            "select * from invoice_item where invoice_item_id = ?";

    private static final String SELECT_ALL_INVOICE_ITEM_SQL =
            "select * from invoice_item";

    private static final String UPDATE_INVOICE_ITEM_SQL =
            "update invoice_item set invoice_id = ?, inventory_id =?,quantity=?,unit_price=? where invoice_item_id=?";

    private static final String DELETE_INVOICE_ITEM_SQL =
            "delete from invoice_item where invoice_item_id = ?";

    private static final String SELECT_INVOICE_ITEM_BY_INVOICEID_SQL =
            "select * from invoice_item where invoice_id = ?";

    //---------------------------------------------------------------------------------//

    //=============================== Implementing Methods from InvoiceItemDao Interface Class ===========================//


    @Autowired
    JdbcTemplate jdbcTemplate;

    public InvoiceItemDaoJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ------------------ Using the jdbcTemplate variable to run each prepared statement and map to the DTO ----------//

    @Override
    @Transactional
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem){
        jdbcTemplate.update(INSERT_INVOICE_ITEM_SQL,
                invoiceItem.getInvoiceId(),
                invoiceItem.getInventoryId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitPrice());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        invoiceItem.setInvoiceItemId(id);

        return invoiceItem;
    }

    @Override
    public InvoiceItem getInvoiceItem(int id){

        try{
            return jdbcTemplate.queryForObject(SELECT_INVOICE_ITEM_SQL, this::mapRowToInvoiceItem,id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItems(){
        return jdbcTemplate.query(SELECT_ALL_INVOICE_ITEM_SQL, this::mapRowToInvoiceItem);
    }

    @Override
    public void updateInvoiceItem(InvoiceItem invoiceItem){
        jdbcTemplate.update(UPDATE_INVOICE_ITEM_SQL,
                invoiceItem.getInvoiceId(),
                invoiceItem.getInventoryId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitPrice(),
                invoiceItem.getInvoiceItemId());
    }

    @Override
    public void deleteInvoiceItem(int id){

        jdbcTemplate.update(DELETE_INVOICE_ITEM_SQL,id);
    }

    @Override
    public List<InvoiceItem> getInvoiceItemsByInvoice(int id) {
        return jdbcTemplate.query(SELECT_INVOICE_ITEM_BY_INVOICEID_SQL, this::mapRowToInvoiceItem,id);
    }

    private InvoiceItem mapRowToInvoiceItem(ResultSet rs, int rowNum) throws SQLException {

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(rs.getInt("invoice_item_id"));
        invoiceItem.setInvoiceId(rs.getInt("invoice_id"));
        invoiceItem.setInventoryId(rs.getInt("inventory_id"));
        invoiceItem.setQuantity(rs.getInt("quantity"));
        invoiceItem.setUnitPrice(rs.getBigDecimal("unit_price"));
        return invoiceItem;

    }
    //----------------------------------------------------------------------------------------------------------------//
    //================================================================================================================//


}
