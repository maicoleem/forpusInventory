package com.forpus.forpusinventory.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "moveinvoice", schema = "inventoryaccounting")
public class MoveinvoiceClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "idInvoice", nullable = false)
    private int idInvoice;
    @Basic
    @Column(name = "date", nullable = false, length = 30)
    private String date;
    @Basic
    @Column(name = "debt", nullable = false)
    private int debt;
    @Basic
    @Column(name = "payCash", nullable = false)
    private int payCash;
    @Basic
    @Column(name = "payBank", nullable = false)
    private int payBank;
    @Basic
    @Column(name = "subtotal", nullable = false)
    private int subtotal;
    @ManyToOne
    @JoinColumn(name = "idInvoice", referencedColumnName = "idInvoice", nullable = false, insertable = false, updatable = false)
    private InvoiceClass invoiceByIdInvoice;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public int getPayCash() {
        return payCash;
    }

    public void setPayCash(int payCash) {
        this.payCash = payCash;
    }

    public int getPayBank() {
        return payBank;
    }

    public void setPayBank(int payBank) {
        this.payBank = payBank;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveinvoiceClass that = (MoveinvoiceClass) o;
        return id == that.id && idInvoice == that.idInvoice && debt == that.debt && payCash == that.payCash && payBank == that.payBank && subtotal == that.subtotal && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idInvoice, date, debt, payCash, payBank, subtotal);
    }

    public InvoiceClass getInvoiceByIdInvoice() {
        return invoiceByIdInvoice;
    }
    public void setInvoiceByIdInvoice(InvoiceClass invoiceByIdInvoice) {
        this.invoiceByIdInvoice = invoiceByIdInvoice;
    }

}
