package com.forpus.forpusinventory.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "assets", schema = "inventoryaccounting", catalog = "")
public class AssetsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAssets", nullable = false)
    private int idAssets;
    @Basic
    @Column(name = "idInvoice", nullable = false)
    private int idInvoice;
    @Basic
    @Column(name = "description", nullable = false, length = 20)
    private String description;
    @Basic
    @Column(name = "debit", nullable = false, precision = 4)
    private BigDecimal debit;
    @Basic
    @Column(name = "credit", nullable = false, precision = 4)
    private BigDecimal credit;

    public int getIdAssets() {
        return idAssets;
    }

    public void setIdAssets(int idAssets) {
        this.idAssets = idAssets;
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetsEntity that = (AssetsEntity) o;
        return idAssets == that.idAssets && idInvoice == that.idInvoice && Objects.equals(description, that.description) && Objects.equals(debit, that.debit) && Objects.equals(credit, that.credit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAssets, idInvoice, description, debit, credit);
    }
}
