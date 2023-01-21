package com.forpus.forpus_inventory.persistence.entity;

import javax.persistence.*;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerClass that = (CustomerClass) o;
        return idCustomer == that.idCustomer && phoneNumber == that.phoneNumber && Objects.equals(name, that.name) && Objects.equals(addres, that.addres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, name, phoneNumber, addres);
    }
}
