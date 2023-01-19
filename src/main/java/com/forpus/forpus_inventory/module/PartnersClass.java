package com.forpus.forpus_inventory.module;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "partners", schema = "inventoryaccounting", catalog = "")
public class PartnersClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdentificationCard", nullable = false)
    private int identificationCard;
    @Basic
    @Column(name = "Name", nullable = false, length = 20)
    private String name;
    @Basic
    @Column(name = "PhoneNumber", nullable = false)
    private int phoneNumber;
    @Basic
    @Column(name = "Address", nullable = false, length = 20)
    private String address;

    public int getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(int identificationCard) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartnersClass that = (PartnersClass) o;
        return identificationCard == that.identificationCard && phoneNumber == that.phoneNumber && Objects.equals(name, that.name) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificationCard, name, phoneNumber, address);
    }
}
