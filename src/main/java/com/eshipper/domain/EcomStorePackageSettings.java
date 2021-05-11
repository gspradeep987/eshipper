package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EcomStorePackageSettings.
 */
@Entity
@Table(name = "ecom_store_package_settings")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EcomStorePackageSettings implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "packing_info_1")
  private Boolean packingInfo1;

  @Column(name = "packing_info_2")
  private Boolean packingInfo2;

  @Column(name = "packing_info_3")
  private Boolean packingInfo3;

  @Column(name = "packing_info_4")
  private Boolean packingInfo4;

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
  @OneToOne(mappedBy = "ecomStorePackageSettings")
  private EcomStore ecomStore;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EcomStorePackageSettings id(Long id) {
    this.id = id;
    return this;
  }

  public Boolean getPackingInfo1() {
    return this.packingInfo1;
  }

  public EcomStorePackageSettings packingInfo1(Boolean packingInfo1) {
    this.packingInfo1 = packingInfo1;
    return this;
  }

  public void setPackingInfo1(Boolean packingInfo1) {
    this.packingInfo1 = packingInfo1;
  }

  public Boolean getPackingInfo2() {
    return this.packingInfo2;
  }

  public EcomStorePackageSettings packingInfo2(Boolean packingInfo2) {
    this.packingInfo2 = packingInfo2;
    return this;
  }

  public void setPackingInfo2(Boolean packingInfo2) {
    this.packingInfo2 = packingInfo2;
  }

  public Boolean getPackingInfo3() {
    return this.packingInfo3;
  }

  public EcomStorePackageSettings packingInfo3(Boolean packingInfo3) {
    this.packingInfo3 = packingInfo3;
    return this;
  }

  public void setPackingInfo3(Boolean packingInfo3) {
    this.packingInfo3 = packingInfo3;
  }

  public Boolean getPackingInfo4() {
    return this.packingInfo4;
  }

  public EcomStorePackageSettings packingInfo4(Boolean packingInfo4) {
    this.packingInfo4 = packingInfo4;
    return this;
  }

  public void setPackingInfo4(Boolean packingInfo4) {
    this.packingInfo4 = packingInfo4;
  }

  public EcomStore getEcomStore() {
    return this.ecomStore;
  }

  public EcomStorePackageSettings ecomStore(EcomStore ecomStore) {
    this.setEcomStore(ecomStore);
    return this;
  }

  public void setEcomStore(EcomStore ecomStore) {
    if (this.ecomStore != null) {
      this.ecomStore.setEcomStorePackageSettings(null);
    }
    if (ecomStore != null) {
      ecomStore.setEcomStorePackageSettings(this);
    }
    this.ecomStore = ecomStore;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomStorePackageSettings)) {
      return false;
    }
    return id != null && id.equals(((EcomStorePackageSettings) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomStorePackageSettings{" +
            "id=" + getId() +
            ", packingInfo1='" + getPackingInfo1() + "'" +
            ", packingInfo2='" + getPackingInfo2() + "'" +
            ", packingInfo3='" + getPackingInfo3() + "'" +
            ", packingInfo4='" + getPackingInfo4() + "'" +
            "}";
    }
}
