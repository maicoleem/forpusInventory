package com.forpus.forpus_inventory.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "customer", schema = "inventoryaccounting")
public class CustomerClass {

    @Id
    @Column(name = "idCustomer", nullable = false, length = 20)
    private String idCustomer;
    @Basic
    @Column(name = "Name", nullable = false, length = 20)
    private String name;
    @Basic
    @Column(name = "PhoneNumber", nullable = false, length = 20)
    private String phoneNumber;
    @Basic
    @Column(name = "Addres", nullable = false, length = 20)
    private String addres;
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
    @OneToMany(mappedBy = "customerByIdCustomer")
    private Collection<InvoiceClass> invoicesByIdCustomer;

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
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

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
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
        CustomerClass that = (CustomerClass) o;
        return Objects.equals(idCustomer, that.idCustomer) && Objects.equals(name, that.name) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(addres, that.addres) && Objects.equals(bank, that.bank) && Objects.equals(cash, that.cash) && Objects.equals(payable, that.payable) && Objects.equals(receivable, that.receivable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, name, phoneNumber, addres, bank, cash, payable, receivable);
    }
}
