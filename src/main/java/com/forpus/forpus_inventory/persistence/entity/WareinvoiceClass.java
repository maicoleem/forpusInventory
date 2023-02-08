package com.forpus.forpus_inventory.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "wareinvoice", schema = "inventoryaccounting", catalog = "")
public class WareinvoiceClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idWareInvoice", nullable = false)
    private int idWareInvoice;
    @Basic
    @Column(name = "idInvoice", nullable = false)
    private int idInvoice;
    @Basic
    @Column(name = "idProduct", nullable = false, length = 6)
    private String idProduct;
    @Basic
    @Column(name = "productName", nullable = false, length = 20)
    private String productName;
    @Basic
    @Column(name = "priceSale", nullable = false, length = 20)
    private String priceSale;
    @Basic
    @Column(name = "idProductPrice", nullable = false)
    private int idProductPrice;
    @Basic
    @Column(name = "priceBuy", nullable = false)
    private int priceBuy;
    @Basic
    @Column(name = "amount", nullable = false)
    private int amount;
    @ManyToOne
    @JoinColumn(name = "idInvoice", referencedColumnName = "idInvoice", nullable = false)
    private InvoiceClass invoiceByIdInvoice;

    public int getIdWareInvoice() {
        return idWareInvoice;
    }

    public void setIdWareInvoice(int idWareInvoice) {
        this.idWareInvoice = idWareInvoice;
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(String priceSale) {
        this.priceSale = priceSale;
    }

    public int getIdProductPrice() {
        return idProductPrice;
    }

    public void setIdProductPrice(int idProductPrice) {
        this.idProductPrice = idProductPrice;
    }

    public int getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(int priceBuy) {
        this.priceBuy = priceBuy;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WareinvoiceClass that = (WareinvoiceClass) o;
        return idWareInvoice == that.idWareInvoice && idInvoice == that.idInvoice && idProductPrice == that.idProductPrice && priceBuy == that.priceBuy && amount == that.amount && Objects.equals(idProduct, that.idProduct) && Objects.equals(productName, that.productName) && Objects.equals(priceSale, that.priceSale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWareInvoice, idInvoice, idProduct, productName, priceSale, idProductPrice, priceBuy, amount);
    }

    public InvoiceClass getInvoiceByIdInvoice() {
        return invoiceByIdInvoice;
    }

    public void setInvoiceByIdInvoice(InvoiceClass invoiceByIdInvoice) {
        this.invoiceByIdInvoice = invoiceByIdInvoice;
    }
}
