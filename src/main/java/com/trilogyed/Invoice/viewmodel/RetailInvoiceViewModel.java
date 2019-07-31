package com.trilogyed.Invoice.viewmodel;

import com.trilogyed.Invoice.model.InvoiceItem;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class RetailInvoiceViewModel {


    private int invoiceId;
    @NotNull(message = "Please supply a Customer ID")
    private int customerId;
    @NotNull(message = "Please supply a Purchase Date")
    private LocalDate purchaseDate;
    @NotNull(message = "Please supply a List of Invoice Items")
    private List<InvoiceItem> invoiceItems;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetailInvoiceViewModel that = (RetailInvoiceViewModel) o;
        return invoiceId == that.invoiceId &&
                customerId == that.customerId &&
                purchaseDate.equals(that.purchaseDate) &&
                invoiceItems.equals(that.invoiceItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, customerId, purchaseDate, invoiceItems);
    }
}
