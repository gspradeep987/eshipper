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
 * A ShipmentService.
 */
@Entity
@Table(name = "shipment_service")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShipmentService implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 255)
  @Column(name = "name", length = 255)
  private String name;

  @ManyToMany(mappedBy = "shipmentServices")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
  private Set<EcomStore> ecomStores = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ShipmentService id(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return this.name;
  }

  public ShipmentService name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<EcomStore> getEcomStores() {
    return this.ecomStores;
  }

  public ShipmentService ecomStores(Set<EcomStore> ecomStores) {
    this.setEcomStores(ecomStores);
    return this;
  }

  public ShipmentService addEcomStore(EcomStore ecomStore) {
    this.ecomStores.add(ecomStore);
    ecomStore.getShipmentServices().add(this);
    return this;
  }

  public ShipmentService removeEcomStore(EcomStore ecomStore) {
    this.ecomStores.remove(ecomStore);
    ecomStore.getShipmentServices().remove(this);
    return this;
  }

  public void setEcomStores(Set<EcomStore> ecomStores) {
    if (this.ecomStores != null) {
      this.ecomStores.forEach(i -> i.removeShipmentService(this));
    }
    if (ecomStores != null) {
      ecomStores.forEach(i -> i.addShipmentService(this));
    }
    this.ecomStores = ecomStores;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ShipmentService)) {
      return false;
    }
    return id != null && id.equals(((ShipmentService) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "ShipmentService{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
