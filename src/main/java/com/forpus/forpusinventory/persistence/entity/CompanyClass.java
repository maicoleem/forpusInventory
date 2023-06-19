package com.forpus.forpusinventory.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "company", schema = "inventoryaccounting")
public class CompanyClass {
    @Id
    @Column(name = "idCompanyNIT", nullable = false, length = 20)
    private String idCompanyNIT;
    @Basic
    @Column(name = "Name", nullable = false, length = 20)
    private String name;
    @Basic
    @Column(name = "Password", nullable = false, length = 10)
    private String password;
    @Basic
    @Column(name = "Addres", nullable = false, length = 20)
    private String addres;
    @Basic
    @Column(name = "PhoneNumber", nullable = false, length = 20)
    private String phoneNumber;
    @Basic
    @Column(name = "Web", nullable = false, length = 20)
    private String web;
    @Basic
    @Column(name = "Social", nullable = false, length = 20)
    private String social;
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
    @Basic
    @Column(name = "uReceivable", nullable = true, length = 20)
    private String uReceivable;
    @Basic
    @Column(name = "utilities", nullable = true, length = 20)
    private String utilities;
    @Basic
    @Column(name = "total", nullable = true, length = 20)
    private String total;

    public Collection<InvoiceClass> getInvoicesByIdCompanyNit() {
        return invoicesByIdCompanyNit;
    }

    public void setInvoicesByIdCompanyNit(Collection<InvoiceClass> invoicesByIdCompanyNit) {
        this.invoicesByIdCompanyNit = invoicesByIdCompanyNit;
    }

    @OneToMany(mappedBy = "companyByIdCompany")
    private Collection<InvoiceClass> invoicesByIdCompanyNit;

    public String getIdCompanyNIT() {
        return idCompanyNIT;
    }

    public void setIdCompanyNIT(String idCompanyNIT) {
        this.idCompanyNIT = idCompanyNIT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
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
    public String getUReceivable() {
        return uReceivable;
    }
    public void setUReceivable(String uReceivable) {
        this.uReceivable = uReceivable;
    }
    public void setReceivable(String receivable) {
        this.receivable = receivable;
    }

    public String getUtilities() {
        return utilities;
    }

    public void setUtilities(String utilities) {
        this.utilities = utilities;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyClass that = (CompanyClass) o;
        return Objects.equals(idCompanyNIT, that.idCompanyNIT) && Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(addres, that.addres) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(web, that.web) && Objects.equals(social, that.social) && Objects.equals(bank, that.bank) && Objects.equals(cash, that.cash) && Objects.equals(payable, that.payable) && Objects.equals(receivable, that.receivable) && Objects.equals(uReceivable, that.uReceivable) && Objects.equals(utilities, that.utilities) && Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompanyNIT, name, password, addres, phoneNumber, web, social, bank, cash, payable, receivable, uReceivable, utilities, total);
    }
}
