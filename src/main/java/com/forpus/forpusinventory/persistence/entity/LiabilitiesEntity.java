package com.forpus.forpusinventory.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "liabilities", schema = "inventoryaccounting", catalog = "")
public class LiabilitiesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idLiabilities", nullable = false)
    private int idLiabilities;
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

    public int getIdLiabilities() {
        return idLiabilities;
    }

    public void setIdLiabilities(int idLiabilities) {
        this.idLiabilities = idLiabilities;
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
        LiabilitiesEntity that = (LiabilitiesEntity) o;
        return idLiabilities == that.idLiabilities && idInvoice == that.idInvoice && Objects.equals(description, that.description) && Objects.equals(debit, that.debit) && Objects.equals(credit, that.credit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLiabilities, idInvoice, description, debit, credit);
    }
}
