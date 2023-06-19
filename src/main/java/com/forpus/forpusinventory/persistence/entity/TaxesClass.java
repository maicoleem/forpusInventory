package com.forpus.forpusinventory.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "taxes", schema = "inventoryaccounting")
public class TaxesClass {
    @Id
    @Basic
    @Column(name = "idTaxes", nullable = false, length = 20)
    private String idTaxes;
    @Basic
    @Column(name = "taxes", nullable = true, length = 6)
    private String taxes;
    public String getIdTaxes() {
        return idTaxes;
    }

    public void setIdTaxes(String idTaxes) {
        this.idTaxes = idTaxes;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxesClass that = (TaxesClass) o;
        return Objects.equals(idTaxes, that.idTaxes) && Objects.equals(taxes, that.taxes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTaxes, taxes);
    }
}
