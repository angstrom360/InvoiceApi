package com.trilogyed.Invoice.viewmodel;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItemViewModel {

    private int invoiceItemId;
    @NotNull(message = "Please supply a Invoice ID")
    private int invoiceId;
    @NotNull(message = "Please supply a Inventory ID")
    private int inventoryId;
    @NotNull(message = "Please supply a quantity")
    private int quantity;
    @NotNull(message = "Please supply a unitPrice")
    private BigDecimal unitPrice;


    public int getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(int invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItemViewModel that = (InvoiceItemViewModel) o;
        return invoiceItemId == that.invoiceItemId &&
                invoiceId == that.invoiceId &&
                inventoryId == that.inventoryId &&
                quantity == that.quantity &&
                unitPrice.equals(that.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceItemId, invoiceId, inventoryId, quantity, unitPrice);
    }
}
