package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EcomStoreShipmentSettings.
 */
@Entity
@Table(name = "ecom_store_shipment_settings")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EcomStoreShipmentSettings implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 20)
  @Column(name = "shipping_mode", length = 20)
  private String shippingMode;

  @Size(max = 255)
  @Column(name = "signature_reqd", length = 255)
  private String signatureReqd;

  @Column(name = "schedule_pickup")
  private Boolean schedulePickup;

  @Column(name = "live_rates")
  private Boolean liveRates;

  @Column(name = "add_residential")
  private Boolean addResidential;

  @Column(name = "tailgate_at_destination")
  private Boolean tailgateAtDestination;

  @Column(name = "tailgate_at_source")
  private Boolean tailgateAtSource;

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
  @OneToOne(mappedBy = "ecomStoreShipmentSettings")
  private EcomStore ecomStore;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EcomStoreShipmentSettings id(Long id) {
    this.id = id;
    return this;
  }

  public String getShippingMode() {
    return this.shippingMode;
  }

  public EcomStoreShipmentSettings shippingMode(String shippingMode) {
    this.shippingMode = shippingMode;
    return this;
  }

  public void setShippingMode(String shippingMode) {
    this.shippingMode = shippingMode;
  }

  public String getSignatureReqd() {
    return this.signatureReqd;
  }

  public EcomStoreShipmentSettings signatureReqd(String signatureReqd) {
    this.signatureReqd = signatureReqd;
    return this;
  }

  public void setSignatureReqd(String signatureReqd) {
    this.signatureReqd = signatureReqd;
  }

  public Boolean getSchedulePickup() {
    return this.schedulePickup;
  }

  public EcomStoreShipmentSettings schedulePickup(Boolean schedulePickup) {
    this.schedulePickup = schedulePickup;
    return this;
  }

  public void setSchedulePickup(Boolean schedulePickup) {
    this.schedulePickup = schedulePickup;
  }

  public Boolean getLiveRates() {
    return this.liveRates;
  }

  public EcomStoreShipmentSettings liveRates(Boolean liveRates) {
    this.liveRates = liveRates;
    return this;
  }

  public void setLiveRates(Boolean liveRates) {
    this.liveRates = liveRates;
  }

  public Boolean getAddResidential() {
    return this.addResidential;
  }

  public EcomStoreShipmentSettings addResidential(Boolean addResidential) {
    this.addResidential = addResidential;
    return this;
  }

  public void setAddResidential(Boolean addResidential) {
    this.addResidential = addResidential;
  }

  public Boolean getTailgateAtDestination() {
    return this.tailgateAtDestination;
  }

  public EcomStoreShipmentSettings tailgateAtDestination(Boolean tailgateAtDestination) {
    this.tailgateAtDestination = tailgateAtDestination;
    return this;
  }

  public void setTailgateAtDestination(Boolean tailgateAtDestination) {
    this.tailgateAtDestination = tailgateAtDestination;
  }

  public Boolean getTailgateAtSource() {
    return this.tailgateAtSource;
  }

  public EcomStoreShipmentSettings tailgateAtSource(Boolean tailgateAtSource) {
    this.tailgateAtSource = tailgateAtSource;
    return this;
  }

  public void setTailgateAtSource(Boolean tailgateAtSource) {
    this.tailgateAtSource = tailgateAtSource;
  }

  public EcomStore getEcomStore() {
    return this.ecomStore;
  }

  public EcomStoreShipmentSettings ecomStore(EcomStore ecomStore) {
    this.setEcomStore(ecomStore);
    return this;
  }

  public void setEcomStore(EcomStore ecomStore) {
    if (this.ecomStore != null) {
      this.ecomStore.setEcomStoreShipmentSettings(null);
    }
    if (ecomStore != null) {
      ecomStore.setEcomStoreShipmentSettings(this);
    }
    this.ecomStore = ecomStore;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomStoreShipmentSettings)) {
      return false;
    }
    return id != null && id.equals(((EcomStoreShipmentSettings) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomStoreShipmentSettings{" +
            "id=" + getId() +
            ", shippingMode='" + getShippingMode() + "'" +
            ", signatureReqd='" + getSignatureReqd() + "'" +
            ", schedulePickup='" + getSchedulePickup() + "'" +
            ", liveRates='" + getLiveRates() + "'" +
            ", addResidential='" + getAddResidential() + "'" +
            ", tailgateAtDestination='" + getTailgateAtDestination() + "'" +
            ", tailgateAtSource='" + getTailgateAtSource() + "'" +
            "}";
    }
}
