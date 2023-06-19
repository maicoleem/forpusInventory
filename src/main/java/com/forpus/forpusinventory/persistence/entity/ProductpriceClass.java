package com.forpus.forpusinventory.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "productprice", schema = "inventoryaccounting")
public class ProductpriceClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPrice", nullable = false)
    private int idPrice;
    @Basic
    @Column(name = "idProductWare", nullable = false, insertable = false, updatable = false)
    private int idProductWare;
    @Basic
    @Column(name = "amount", nullable = false)
    private int amount;
    @Basic
    @Column(name = "price", nullable = false)
    private int price;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idProductWare", referencedColumnName = "id_ware_product", nullable = false)
    private WareProductClass wareProductByIdProductWare;

    public int getIdPrice() {
        return idPrice;
    }

    public void setIdPrice(int idPrice) {
        this.idPrice = idPrice;
    }

    public int getIdProductWare() {
        return idProductWare;
    }

    public void setIdProductWare(int idProductWare) {
        this.idProductWare = idProductWare;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductpriceClass that = (ProductpriceClass) o;
        return idPrice == that.idPrice && idProductWare == that.idProductWare && amount == that.amount && price == that.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrice, idProductWare, amount, price);
    }

    public WareProductClass getWareProductByIdProductWare() {
        return wareProductByIdProductWare;
    }

    public void setWareProductByIdProductWare(WareProductClass wareProductByIdProductWare) {
        this.wareProductByIdProductWare = wareProductByIdProductWare;
    }

}
