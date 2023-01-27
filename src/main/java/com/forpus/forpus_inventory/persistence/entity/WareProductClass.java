package com.forpus.forpus_inventory.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ware_product", schema = "inventoryaccounting")
public class WareProductClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_ware_product", nullable=false)
    private int idWareProduct;
    @Basic
    @Column(name = "id_product", insertable = false, updatable = false, nullable = false, length = 6)
    private String idProduct;
    @Basic
    @Column(name = "id_ware", insertable = false, updatable = false, nullable = false, length = 6)
    private String idWare;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_product", referencedColumnName = "id_product", nullable = false)
    private ProductClass productByIdProduct;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ware", referencedColumnName = "id_warehouse", nullable = false)
    private WarehouseClass warehouseByIdWare;
    @OneToMany(mappedBy = "wareProductByIdProductWare")
    private Collection<ProductpriceClass> productpricesByIdWareProduct;

    public int getIdWareProduct() {
        return idWareProduct;
    }

    public void setIdWareProduct(int idWareProduct) {
        this.idWareProduct = idWareProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdWare() {
        return idWare;
    }

    public void setIdWare(String idWare) {
        this.idWare = idWare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WareProductClass that = (WareProductClass) o;
        return idWareProduct == that.idWareProduct && Objects.equals(idProduct, that.idProduct) && Objects.equals(idWare, that.idWare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWareProduct, idProduct, idWare);
    }

    public ProductClass getProductByIdProduct() {
        return productByIdProduct;
    }

    public void setProductByIdProduct(ProductClass productByIdProduct) {
        this.productByIdProduct = productByIdProduct;
    }

    public WarehouseClass getWarehouseByIdWare() {
        return warehouseByIdWare;
    }

    public void setWarehouseByIdWare(WarehouseClass warehouseByIdWare) {
        this.warehouseByIdWare = warehouseByIdWare;
    }
    public void setProductpricesByIdWareProduct(Collection<ProductpriceClass> productpricesByIdWareProduct) {
        this.productpricesByIdWareProduct = productpricesByIdWareProduct;
    }
    public Collection<ProductpriceClass> getProductpricesByIdWareProduct() {
        return productpricesByIdWareProduct;
    }
}
