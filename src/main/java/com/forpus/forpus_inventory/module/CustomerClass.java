package com.forpus.forpus_inventory.module;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer", schema = "inventoryaccounting", catalog = "")
public class CustomerClass {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCustomer", nullable = false)
    private int idCustomer;
    @Basic
    @Column(name = "IdentificationCard", nullable = false, length = 15)
    private String identificationCard;
    @Basic
    @Column(name = "Name", nullable = false, length = 20)
    private String name;
    @Basic
    @Column(name = "PhoneNumber", nullable = false)
    private int phoneNumber;
    @Basic
    @Column(name = "Addres", nullable = false, length = 20)
    private String addres;

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
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
        return idCustomer == that.idCustomer && phoneNumber == that.phoneNumber && Objects.equals(identificationCard, that.identificationCard) && Objects.equals(name, that.name) && Objects.equals(addres, that.addres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, identificationCard, name, phoneNumber, addres);
    }
}
