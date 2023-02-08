package com.forpus.forpus_inventory.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "providers", schema = "inventoryaccounting")
public class ProvidersClass {
    @Id
    @Column(name = "Nit", nullable = false, length = 20)
    private String nit;
    @Basic
    @Column(name = "Name", nullable = false, length = 20)
    private String name;
    @Basic
    @Column(name = "PhoneNumber", nullable = false, length = 15)
    private String phoneNumber;
    @Basic
    @Column(name = "Address", nullable = false, length = 20)
    private String address;
    @Basic
    @Column(name = "Email", nullable = false, length = 20)
    private String email;
    @Basic
    @Column(name = "bank", nullable = true, length = 20)
    private String bank;
    @Basic
    @Column(name = "cash", nullable = true, length = 20)
    private String cash;
    @Basic
    @Column(name = "payable", nullable = true, length = 20)
    private String payable;
    @Basic
    @Column(name = "receivable", nullable = true, length = 20)
    private String receivable;
    @OneToMany(mappedBy = "providersByIdCustomer")
    private Collection<InvoiceClass> invoicesByNit;

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getPayable() {
        return payable;
    }

    public void setPayable(String payable) {
        this.payable = payable;
    }

    public String getReceivable() {
        return receivable;
    }

    public void setReceivable(String receivable) {
        this.receivable = receivable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProvidersClass that = (ProvidersClass) o;
        return Objects.equals(nit, that.nit) && Objects.equals(name, that.name) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address) && Objects.equals(email, that.email) && Objects.equals(bank, that.bank) && Objects.equals(cash, that.cash) && Objects.equals(payable, that.payable) && Objects.equals(receivable, that.receivable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nit, name, phoneNumber, address, email, bank, cash, payable, receivable);
    }
    public Collection<InvoiceClass> getInvoicesByNit() {
        return invoicesByNit;
    }
    public void setInvoicesByNit(Collection<InvoiceClass> invoicesByNit) {
        this.invoicesByNit = invoicesByNit;
    }
}
