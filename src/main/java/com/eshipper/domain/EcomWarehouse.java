package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EcomWarehouse.
 */
@Entity
@Table(name = "ecom_warehouse")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "ecomProductImages", "country", "ecomWarehouses", "ecomOrder" }, allowSetters = true)
  private Set<EcomProduct> ecomProducts = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EcomWarehouse id(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return this.name;
  }

  public EcomWarehouse name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return this.address;
  }

  public EcomWarehouse address(String address) {
    this.address = address;
    return this;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Set<EcomProduct> getEcomProducts() {
    return this.ecomProducts;
  }

  public EcomWarehouse ecomProducts(Set<EcomProduct> ecomProducts) {
    this.setEcomProducts(ecomProducts);
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
    if (this.ecomProducts != null) {
      this.ecomProducts.forEach(i -> i.removeEcomWarehouse(this));
    }
    if (ecomProducts != null) {
      ecomProducts.forEach(i -> i.addEcomWarehouse(this));
    }
    this.ecomProducts = ecomProducts;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

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
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomWarehouse{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
