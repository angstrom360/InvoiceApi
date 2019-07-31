package com.trilogyed.Invoice.viewmodel;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class InvoiceViewModel {

    private int invoiceId;
    @NotNull(message="Please supply a customer ID.")
    private int customerId;
    @NotNull(message = "Please enter a purchase date.")
    private LocalDate purchaseDate;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return invoiceId == that.invoiceId &&
                customerId == that.customerId &&
                purchaseDate.equals(that.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, customerId, purchaseDate);
    }
}
