package com.forpus.forpusinventory.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "inventoryaccounting")
public class ProductClass {
    @Id
    @Column(name = "id_product", nullable = false, length = 20)
    private String idProduct;
    @Basic
    @Column(name = "name", nullable = false, length = 60)
    private String name;
    @Basic
    @Column(name = "purchase_price", nullable = false, length = 20)
    private String purchasePrice;
    @Basic
    @Column(name = "amount", nullable = false, length = 6)
    private Integer amount;
    @Basic
    @Column(name = "sale_price", nullable = false, length = 20)
    private String salePrice;
    @Basic
    @Column(name = "profit", nullable = false, length = 20)
    private String profit;
    @Basic
    @Column(name = "id_one", nullable = true)
    private Integer idOne;
    @Basic
    @Column(name = "id_two", nullable = true)
    private Integer idTwo;
    @Basic
    @Column(name = "id_three", nullable = true)
    private Integer idThree;
    @Basic
    @Column(name = "id_wage", nullable = true, length = 6)
    private String idWage;
    @Basic
    @Column(name = "id_Service", nullable = true, length = 6)
    private String idService;
    @Basic
    @Column(name = "off_sale", nullable = true)
    private Integer offSale;
    @Basic
    @Column(name = "stock", nullable = true, length = 6)
    private Integer stock;
    @OneToMany(mappedBy = "productByIdProduct")
    private Collection<ServiceProductClass> serviceProductsByIdProduct;
    @OneToMany(mappedBy = "productByIdProduct", cascade = CascadeType.PERSIST)
    private Collection<WareProductClass> wareProductsByIdProduct;
    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public Integer getAmount() { return amount; }

    public void setAmount(Integer amount) { this.amount = amount;}

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public Integer getIdOne() {
        return idOne;
    }

    public void setIdOne(Integer idOne) {
        this.idOne = idOne;
    }

    public Integer getIdTwo() {
        return idTwo;
    }

    public void setIdTwo(Integer idTwo) {
        this.idTwo = idTwo;
    }

    public Integer getIdThree() {
        return idThree;
    }

    public void setIdThree(Integer idThree) {
        this.idThree = idThree;
    }

    public String getIdWage() {
        return idWage;
    }

    public void setIdWage(String idWage) {
        this.idWage = idWage;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductClass that = (ProductClass) o;
        return Objects.equals(idProduct, that.idProduct) && Objects.equals(name, that.name) && Objects.equals(purchasePrice, that.purchasePrice) && Objects.equals(amount, that.amount) && Objects.equals(salePrice, that.salePrice) && Objects.equals(profit, that.profit) && Objects.equals(idOne, that.idOne) && Objects.equals(idTwo, that.idTwo) && Objects.equals(idThree, that.idThree) && Objects.equals(idWage, that.idWage) && Objects.equals(idService, that.idService) && Objects.equals(offSale, that.offSale) && Objects.equals(stock, that.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, name, purchasePrice, amount, salePrice, profit, idOne, idTwo, idThree, idWage, idService, offSale, stock);
    }

    public Collection<ServiceProductClass> getServiceProductsByIdProduct() {
        return serviceProductsByIdProduct;
    }

    public void setServiceProductsByIdProduct(Collection<ServiceProductClass> serviceProductsByIdProduct) {
        this.serviceProductsByIdProduct = serviceProductsByIdProduct;
    }

    public Collection<WareProductClass> getWareProductsByIdProduct() {
        return wareProductsByIdProduct;
    }

    public void setWareProductsByIdProduct(Collection<WareProductClass> wareProductsByIdProduct) {
        this.wareProductsByIdProduct = wareProductsByIdProduct;
    }

    public Integer getOffSale() {
        return offSale;
    }

    public void setOffSale(Integer offSale) {
        this.offSale = offSale;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
