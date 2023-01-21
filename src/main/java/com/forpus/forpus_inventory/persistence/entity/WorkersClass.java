package com.forpus.forpus_inventory.persistence.entity;

import javax.persistence.*;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkersClass that = (WorkersClass) o;
        return identificationCard == that.identificationCard && wage == that.wage && phoneNumber == that.phoneNumber && Objects.equals(name, that.name) && Objects.equals(job, that.job) && Objects.equals(address, that.address) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificationCard, name, job, wage, phoneNumber, address, password);
    }
}
