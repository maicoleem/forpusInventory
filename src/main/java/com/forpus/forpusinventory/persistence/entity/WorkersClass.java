package com.forpus.forpusinventory.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "workers", schema = "inventoryaccounting")
public class WorkersClass {
    @Id
    @Column(name = "IdentificationCard", nullable = false, length = 20)
    private String identificationCard;
    @Basic
    @Column(name = "Name", nullable = false, length = 20)
    private String name;
    @Basic
    @Column(name = "Job", nullable = false, length = 20)
    private String job;
    @Basic
    @Column(name = "Wage", nullable = false, length = 20)
    private String wage;
    @Basic
    @Column(name = "PhoneNumber", nullable = false, length = 20)
    private String phoneNumber;
    @Basic
    @Column(name = "Address", nullable = false, length = 20)
    private String address;
    @Basic
    @Column(name = "Password", nullable = false, length = 15)
    private String password;
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
    @OneToMany(mappedBy = "workersByIdWorkers")
    private Collection<InvoiceClass> invoicesByIdentificationCard;

    public String getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(String identificationCard) {
        this.identificationCard = identificationCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        WorkersClass that = (WorkersClass) o;
        return Objects.equals(identificationCard, that.identificationCard) && Objects.equals(name, that.name) && Objects.equals(job, that.job) && Objects.equals(wage, that.wage) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address) && Objects.equals(password, that.password) && Objects.equals(bank, that.bank) && Objects.equals(cash, that.cash) && Objects.equals(payable, that.payable) && Objects.equals(receivable, that.receivable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificationCard, name, job, wage, phoneNumber, address, password, bank, cash, payable, receivable);
    }
    public Collection<InvoiceClass> getInvoicesByIdentificationCard() {
        return invoicesByIdentificationCard;
    }

    public void setInvoicesByIdentificationCard(Collection<InvoiceClass> invoicesByIdentificationCard) {
        this.invoicesByIdentificationCard = invoicesByIdentificationCard;
    }
}
