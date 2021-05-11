package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EcomStoreColorTheme.
 */
@Entity
@Table(name = "ecom_store_color_theme")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EcomStoreColorTheme implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 255)
  @Column(name = "jhi_primary", length = 255)
  private String primary;

  @Size(max = 255)
  @Column(name = "secondary", length = 255)
  private String secondary;

  @JsonIgnoreProperties(
    value = {
      "ecomStoreAddress",
      "ecomStoreColorTheme",
      "ecomStoreShipmentSettings",
      "ecomStorePackageSettings",
      "ecomStoreMarkup",
      "ecomMailTemplates",
      "ecomOrders",
      "company",
      "user",
      "ecomProduct",
      "shipmentServices",
    },
    allowSetters = true
  )
  @OneToOne(mappedBy = "ecomStoreColorTheme")
  private EcomStore ecomStore;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EcomStoreColorTheme id(Long id) {
    this.id = id;
    return this;
  }

  public String getPrimary() {
    return this.primary;
  }

  public EcomStoreColorTheme primary(String primary) {
    this.primary = primary;
    return this;
  }

  public void setPrimary(String primary) {
    this.primary = primary;
  }

  public String getSecondary() {
    return this.secondary;
  }

  public EcomStoreColorTheme secondary(String secondary) {
    this.secondary = secondary;
    return this;
  }

  public void setSecondary(String secondary) {
    this.secondary = secondary;
  }

  public EcomStore getEcomStore() {
    return this.ecomStore;
  }

  public EcomStoreColorTheme ecomStore(EcomStore ecomStore) {
    this.setEcomStore(ecomStore);
    return this;
  }

  public void setEcomStore(EcomStore ecomStore) {
    if (this.ecomStore != null) {
      this.ecomStore.setEcomStoreColorTheme(null);
    }
    if (ecomStore != null) {
      ecomStore.setEcomStoreColorTheme(this);
    }
    this.ecomStore = ecomStore;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomStoreColorTheme)) {
      return false;
    }
    return id != null && id.equals(((EcomStoreColorTheme) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomStoreColorTheme{" +
            "id=" + getId() +
            ", primary='" + getPrimary() + "'" +
            ", secondary='" + getSecondary() + "'" +
            "}";
    }
}
