package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EcomStoreMarkup.
 */
@Entity
@Table(name = "ecom_store_markup")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EcomStoreMarkup implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 20)
  @Column(name = "markup_type", length = 20)
  private String markupType;

  @Column(name = "min_value")
  private Float minValue;

  @Column(name = "domestic_value")
  private Float domesticValue;

  @Column(name = "intl_value")
  private Float intlValue;

  @Column(name = "flat_rate_value")
  private Float flatRateValue;

  @Column(name = "opex_value")
  private Float opexValue;

  @JsonIgnoreProperties(value = { "ecomStoreMarkup" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private EcomMarkupPrimary ecomMarkupPrimary;

  @JsonIgnoreProperties(value = { "ecomStoreMarkup" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private EcomMarkupSecondary ecomMarkupSecondary;

  @JsonIgnoreProperties(value = { "ecomStoreMarkup" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private EcomMarkupTertiary ecomMarkupTertiary;

  @JsonIgnoreProperties(value = { "country", "ecomStoreMarkup" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private EcomMarkupQuaternary ecomMarkupQuaternary;

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
  @OneToOne(mappedBy = "ecomStoreMarkup")
  private EcomStore ecomStore;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EcomStoreMarkup id(Long id) {
    this.id = id;
    return this;
  }

  public String getMarkupType() {
    return this.markupType;
  }

  public EcomStoreMarkup markupType(String markupType) {
    this.markupType = markupType;
    return this;
  }

  public void setMarkupType(String markupType) {
    this.markupType = markupType;
  }

  public Float getMinValue() {
    return this.minValue;
  }

  public EcomStoreMarkup minValue(Float minValue) {
    this.minValue = minValue;
    return this;
  }

  public void setMinValue(Float minValue) {
    this.minValue = minValue;
  }

  public Float getDomesticValue() {
    return this.domesticValue;
  }

  public EcomStoreMarkup domesticValue(Float domesticValue) {
    this.domesticValue = domesticValue;
    return this;
  }

  public void setDomesticValue(Float domesticValue) {
    this.domesticValue = domesticValue;
  }

  public Float getIntlValue() {
    return this.intlValue;
  }

  public EcomStoreMarkup intlValue(Float intlValue) {
    this.intlValue = intlValue;
    return this;
  }

  public void setIntlValue(Float intlValue) {
    this.intlValue = intlValue;
  }

  public Float getFlatRateValue() {
    return this.flatRateValue;
  }

  public EcomStoreMarkup flatRateValue(Float flatRateValue) {
    this.flatRateValue = flatRateValue;
    return this;
  }

  public void setFlatRateValue(Float flatRateValue) {
    this.flatRateValue = flatRateValue;
  }

  public Float getOpexValue() {
    return this.opexValue;
  }

  public EcomStoreMarkup opexValue(Float opexValue) {
    this.opexValue = opexValue;
    return this;
  }

  public void setOpexValue(Float opexValue) {
    this.opexValue = opexValue;
  }

  public EcomMarkupPrimary getEcomMarkupPrimary() {
    return this.ecomMarkupPrimary;
  }

  public EcomStoreMarkup ecomMarkupPrimary(EcomMarkupPrimary ecomMarkupPrimary) {
    this.setEcomMarkupPrimary(ecomMarkupPrimary);
    return this;
  }

  public void setEcomMarkupPrimary(EcomMarkupPrimary ecomMarkupPrimary) {
    this.ecomMarkupPrimary = ecomMarkupPrimary;
  }

  public EcomMarkupSecondary getEcomMarkupSecondary() {
    return this.ecomMarkupSecondary;
  }

  public EcomStoreMarkup ecomMarkupSecondary(EcomMarkupSecondary ecomMarkupSecondary) {
    this.setEcomMarkupSecondary(ecomMarkupSecondary);
    return this;
  }

  public void setEcomMarkupSecondary(EcomMarkupSecondary ecomMarkupSecondary) {
    this.ecomMarkupSecondary = ecomMarkupSecondary;
  }

  public EcomMarkupTertiary getEcomMarkupTertiary() {
    return this.ecomMarkupTertiary;
  }

  public EcomStoreMarkup ecomMarkupTertiary(EcomMarkupTertiary ecomMarkupTertiary) {
    this.setEcomMarkupTertiary(ecomMarkupTertiary);
    return this;
  }

  public void setEcomMarkupTertiary(EcomMarkupTertiary ecomMarkupTertiary) {
    this.ecomMarkupTertiary = ecomMarkupTertiary;
  }

  public EcomMarkupQuaternary getEcomMarkupQuaternary() {
    return this.ecomMarkupQuaternary;
  }

  public EcomStoreMarkup ecomMarkupQuaternary(EcomMarkupQuaternary ecomMarkupQuaternary) {
    this.setEcomMarkupQuaternary(ecomMarkupQuaternary);
    return this;
  }

  public void setEcomMarkupQuaternary(EcomMarkupQuaternary ecomMarkupQuaternary) {
    this.ecomMarkupQuaternary = ecomMarkupQuaternary;
  }

  public EcomStore getEcomStore() {
    return this.ecomStore;
  }

  public EcomStoreMarkup ecomStore(EcomStore ecomStore) {
    this.setEcomStore(ecomStore);
    return this;
  }

  public void setEcomStore(EcomStore ecomStore) {
    if (this.ecomStore != null) {
      this.ecomStore.setEcomStoreMarkup(null);
    }
    if (ecomStore != null) {
      ecomStore.setEcomStoreMarkup(this);
    }
    this.ecomStore = ecomStore;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomStoreMarkup)) {
      return false;
    }
    return id != null && id.equals(((EcomStoreMarkup) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomStoreMarkup{" +
            "id=" + getId() +
            ", markupType='" + getMarkupType() + "'" +
            ", minValue=" + getMinValue() +
            ", domesticValue=" + getDomesticValue() +
            ", intlValue=" + getIntlValue() +
            ", flatRateValue=" + getFlatRateValue() +
            ", opexValue=" + getOpexValue() +
            "}";
    }
}
