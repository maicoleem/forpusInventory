package com.forpus.forpus_inventory.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "service_product", schema = "inventoryaccounting")
public class ServiceProductClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "id_service", insertable = false, updatable = false, nullable = false, length = 6)
    private String idService;
    @Basic
    @Column(name = "id_product",insertable = false, updatable = false, nullable = false, length = 6)
    private String idProduct;
    @Basic
    @Column(name = "contents", nullable = false, length = 20)
    private String contents;
    @Basic
    @Column(name = "consume", nullable = false, length = 20)
    private String consume;
    @Basic
    @Column(name = "profit", nullable = false, length = 20)
    private String profit;
    @ManyToOne
    @JoinColumn(name = "id_service", referencedColumnName = "id_service", nullable = false)
    private ServiceClass serviceByIdService;
    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id_product", nullable = false)
    private ProductClass productByIdProduct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
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
        ServiceProductClass that = (ServiceProductClass) o;
        return id == that.id && Objects.equals(idService, that.idService) && Objects.equals(idProduct, that.idProduct) && Objects.equals(contents, that.contents) && Objects.equals(consume, that.consume) && Objects.equals(profit, that.profit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idService, idProduct, contents, consume, profit);
    }

    public ServiceClass getServiceByIdService() {
        return serviceByIdService;
    }

    public void setServiceByIdService(ServiceClass serviceByIdService) {
        this.serviceByIdService = serviceByIdService;
    }

    public ProductClass getProductByIdProduct() {
        return productByIdProduct;
    }

    public void setProductByIdProduct(ProductClass productByIdProduct) {
        this.productByIdProduct = productByIdProduct;
    }
}
