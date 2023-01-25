package com.forpus.forpus_inventory.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categorythree", schema = "inventoryaccounting")
public class CategorythreeClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_three", nullable = false)
    private int idThree;
    @Basic
    @Column(name = "categoryThree", nullable = false, length = 20)
    private String categoryThree;
    @Basic
    @Column(name = "id_two_three", insertable = false, updatable = false, nullable = false)
    private int idTwoThree;
    @ManyToOne
    @JoinColumn(name = "id_two_three", referencedColumnName = "id_two", nullable = false)
    private CategorytwoClass categorytwoByIdTwoThree;

    public int getIdThree() {
        return idThree;
    }

    public void setIdThree(int idThree) {
        this.idThree = idThree;
    }

    public String getCategoryThree() {
        return categoryThree;
    }

    public void setCategoryThree(String categoryThree) {
        this.categoryThree = categoryThree;
    }

    public int getIdTwoThree() {
        return idTwoThree;
    }

    public void setIdTwoThree(int idTwoThree) {
        this.idTwoThree = idTwoThree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorythreeClass that = (CategorythreeClass) o;
        return idThree == that.idThree && idTwoThree == that.idTwoThree && Objects.equals(categoryThree, that.categoryThree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idThree, categoryThree, idTwoThree);
    }

    public CategorytwoClass getCategorytwoByIdTwoThree() {
        return categorytwoByIdTwoThree;
    }

    public void setCategorytwoByIdTwoThree(CategorytwoClass categorytwoByIdTwoThree) {
        this.categorytwoByIdTwoThree = categorytwoByIdTwoThree;
    }
}
