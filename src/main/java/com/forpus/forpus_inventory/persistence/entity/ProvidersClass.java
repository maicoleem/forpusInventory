package com.forpus.forpus_inventory.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "providers", schema = "inventoryaccounting", catalog = "")
public class ProvidersClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProvidersClass that = (ProvidersClass) o;
        return Objects.equals(nit, that.nit) && Objects.equals(name, that.name) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nit, name, phoneNumber, address, email);
    }
}
