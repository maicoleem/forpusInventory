package com.forpus.forpusinventory.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "service", schema = "inventoryaccounting")
public class ServiceClass {
    @Id
    @Column(name = "id_service", nullable = false, length = 6)
    private String idService;
    @Basic
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Basic
    @Column(name = "payForHour", nullable = false, length = 20)
    private String payForHour;
    @Basic
    @Column(name = "hour", nullable = false, length = 20)
    private String hour;
    @Basic
    @Column(name = "cost", nullable = true, length = 20)
    private String cost;
    @Basic
    @Column(name = "id_ware", insertable = false, updatable = false, nullable = true, length = 6)
    private String idWare;
    @Basic
    @Column(name = "profit", insertable = false, updatable = false, nullable = false, length = 6)
    private String profit;
    @ManyToOne
    @JoinColumn(name = "id_ware", referencedColumnName = "id_warehouse")
    private WarehouseClass warehouseByIdWare;
    @OneToMany(mappedBy = "serviceByIdService", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<ServiceProductClass> serviceProductsByIdService;

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayForHour() {
        return payForHour;
    }

    public void setPayForHour(String payForHour) {
        this.payForHour = payForHour;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getIdWare() {
        return idWare;
    }

    public void setIdWare(String idWare) {
        this.idWare = idWare;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceClass that = (ServiceClass) o;
        return Objects.equals(idService, that.idService) && Objects.equals(name, that.name) && Objects.equals(payForHour, that.payForHour) && Objects.equals(hour, that.hour) && Objects.equals(cost, that.cost) && Objects.equals(idWare, that.idWare) && Objects.equals(profit, that.profit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idService, name, payForHour, hour, cost, idWare, profit);
    }

    public WarehouseClass getWarehouseByIdWare() {
        return warehouseByIdWare;
    }

    public void setWarehouseByIdWare(WarehouseClass warehouseByIdWare) {
        this.warehouseByIdWare = warehouseByIdWare;
    }

    public Collection<ServiceProductClass> getServiceProductsByIdService() {
        return serviceProductsByIdService;
    }

    public void setServiceProductsByIdService(Collection<ServiceProductClass> serviceProductsByIdService) {
        this.serviceProductsByIdService = serviceProductsByIdService;
    }
}
