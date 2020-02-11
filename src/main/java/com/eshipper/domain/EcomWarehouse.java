package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A EcomWarehouse.
 */
@Entity
@Table(name = "ecom_warehouse")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EcomWarehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Size(max = 50)
    @Column(name = "address", length = 50)
    private String address;

    @ManyToMany(mappedBy = "ecomWarehouses")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<EcomProduct> ecomProducts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public EcomWarehouse name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public EcomWarehouse address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<EcomProduct> getEcomProducts() {
        return ecomProducts;
    }

    public EcomWarehouse ecomProducts(Set<EcomProduct> ecomProducts) {
        this.ecomProducts = ecomProducts;
        return this;
    }

    public EcomWarehouse addEcomProduct(EcomProduct ecomProduct) {
        this.ecomProducts.add(ecomProduct);
        ecomProduct.getEcomWarehouses().add(this);
        return this;
    }

    public EcomWarehouse removeEcomProduct(EcomProduct ecomProduct) {
        this.ecomProducts.remove(ecomProduct);
        ecomProduct.getEcomWarehouses().remove(this);
        return this;
    }

    public void setEcomProducts(Set<EcomProduct> ecomProducts) {
        this.ecomProducts = ecomProducts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EcomWarehouse)) {
            return false;
        }
        return id != null && id.equals(((EcomWarehouse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EcomWarehouse{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
