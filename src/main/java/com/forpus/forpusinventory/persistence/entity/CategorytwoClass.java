package com.forpus.forpusinventory.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "categorytwo", schema = "inventoryaccounting")
public class CategorytwoClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_two", nullable = false)
    private int idTwo;
    @Basic
    @Column(name = "categoryTwo", nullable = false, length = 20)
    private String categoryTwo;
    @Basic
    @Column(name = "id_category_one", insertable = false, updatable = false, nullable = false)
    private int idCategoryOne;
    @OneToMany(mappedBy = "categorytwoByIdTwoThree", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<CategorythreeClass> categorythreesByIdTwo;
    @ManyToOne
    @JoinColumn(name = "id_category_one", referencedColumnName = "id_one", nullable = false)
    private CategoryoneClass categoryoneByIdCategoryOne;

    public int getIdTwo() {
        return idTwo;
    }

    public void setIdTwo(int idTwo) {
        this.idTwo = idTwo;
    }

    public String getCategoryTwo() {
        return categoryTwo;
    }

    public void setCategoryTwo(String categoryTwo) {
        this.categoryTwo = categoryTwo;
    }

    public int getIdCategoryOne() {
        return idCategoryOne;
    }

    public void setIdCategoryOne(int idCategoryOne) {
        this.idCategoryOne = idCategoryOne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorytwoClass that = (CategorytwoClass) o;
        return idTwo == that.idTwo && idCategoryOne == that.idCategoryOne && Objects.equals(categoryTwo, that.categoryTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTwo, categoryTwo, idCategoryOne);
    }

    public Collection<CategorythreeClass> getCategorythreesByIdTwo() {
        return categorythreesByIdTwo;
    }

    public void setCategorythreesByIdTwo(Collection<CategorythreeClass> categorythreesByIdTwo) {
        this.categorythreesByIdTwo = categorythreesByIdTwo;
    }

    public CategoryoneClass getCategoryoneByIdCategoryOne() {
        return categoryoneByIdCategoryOne;
    }

    public void setCategoryoneByIdCategoryOne(CategoryoneClass categoryoneByIdCategoryOne) {
        this.categoryoneByIdCategoryOne = categoryoneByIdCategoryOne;
    }
}
