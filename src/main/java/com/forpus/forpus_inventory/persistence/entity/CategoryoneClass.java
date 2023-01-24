package com.forpus.forpus_inventory.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "categoryone", schema = "inventoryaccounting")
public class CategoryoneClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_one", nullable = false)
    private int idOne;
    @Basic
    @Column(name = "categoryOne", nullable = false, length = 20)
    private String categoryOne;
    @OneToMany(mappedBy = "categoryoneByIdCategoryOne")
    private Collection<CategorytwoClass> categorytwosByIdOne;

    public int getIdOne() {
        return idOne;
    }

    public void setIdOne(int idOne) {
        this.idOne = idOne;
    }

    public String getCategoryOne() {
        return categoryOne;
    }

    public void setCategoryOne(String categoryOne) {
        this.categoryOne = categoryOne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryoneClass that = (CategoryoneClass) o;
        return idOne == that.idOne && Objects.equals(categoryOne, that.categoryOne);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOne, categoryOne);
    }

    public Collection<CategorytwoClass> getCategorytwosByIdOne() {
        return categorytwosByIdOne;
    }

    public void setCategorytwosByIdOne(Collection<CategorytwoClass> categorytwosByIdOne) {
        this.categorytwosByIdOne = categorytwosByIdOne;
    }
}
