package com.forpus.forpus_inventory.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "warehouse", schema = "inventoryaccounting", catalog = "")
public class WarehouseClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_warehouse", nullable = false, length = 6)
    private String idWarehouse;
    @Basic
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @OneToMany(mappedBy = "warehouseByIdWare")
    private Collection<ServiceClass> servicesByIdWarehouse;
    @OneToMany(mappedBy = "warehouseByIdWare")
    private Collection<WareProductClass> wareProductsByIdWarehouse;

    public String getIdWarehouse() {
        return idWarehouse;
    }

    public void setIdWarehouse(String idWarehouse) {
        this.idWarehouse = idWarehouse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseClass that = (WarehouseClass) o;
        return Objects.equals(idWarehouse, that.idWarehouse) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWarehouse, name);
    }

    public Collection<ServiceClass> getServicesByIdWarehouse() {
        return servicesByIdWarehouse;
    }

    public void setServicesByIdWarehouse(Collection<ServiceClass> servicesByIdWarehouse) {
        this.servicesByIdWarehouse = servicesByIdWarehouse;
    }

    public Collection<WareProductClass> getWareProductsByIdWarehouse() {
        return wareProductsByIdWarehouse;
    }

    public void setWareProductsByIdWarehouse(Collection<WareProductClass> wareProductsByIdWarehouse) {
        this.wareProductsByIdWarehouse = wareProductsByIdWarehouse;
    }
}
