package com.forpus.forpus_inventory.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "company", schema = "inventoryaccounting", catalog = "")
public class CompanyClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCompany", nullable = false)
    private int idCompany;
    @Basic
    @Column(name = "Name", nullable = false, length = 10)
    private String name;
    @Basic
    @Column(name = "Password", nullable = false, length = 10)
    private String password;
    @Basic
    @Column(name = "NIT", nullable = false, length = 10)
    private String nit;
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

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyClass that = (CompanyClass) o;
        return idCompany == that.idCompany && Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(nit, that.nit) && Objects.equals(addres, that.addres) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(web, that.web) && Objects.equals(social, that.social);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompany, name, password, nit, addres, phoneNumber, web, social);
    }
}
