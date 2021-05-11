package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.eshipper.domain.EcomStoreMarkup} entity.
 */
public class EcomStoreMarkupDTO implements Serializable {

  private Long id;

  @Size(max = 20)
  private String markupType;

  private Float minValue;

  private Float domesticValue;

  private Float intlValue;

  private Float flatRateValue;

  private Float opexValue;

  private EcomMarkupPrimaryDTO ecomMarkupPrimary;

  private EcomMarkupSecondaryDTO ecomMarkupSecondary;

  private EcomMarkupTertiaryDTO ecomMarkupTertiary;

  private EcomMarkupQuaternaryDTO ecomMarkupQuaternary;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMarkupType() {
    return markupType;
  }

  public void setMarkupType(String markupType) {
    this.markupType = markupType;
  }

  public Float getMinValue() {
    return minValue;
  }

  public void setMinValue(Float minValue) {
    this.minValue = minValue;
  }

  public Float getDomesticValue() {
    return domesticValue;
  }

  public void setDomesticValue(Float domesticValue) {
    this.domesticValue = domesticValue;
  }

  public Float getIntlValue() {
    return intlValue;
  }

  public void setIntlValue(Float intlValue) {
    this.intlValue = intlValue;
  }

  public Float getFlatRateValue() {
    return flatRateValue;
  }

  public void setFlatRateValue(Float flatRateValue) {
    this.flatRateValue = flatRateValue;
  }

  public Float getOpexValue() {
    return opexValue;
  }

  public void setOpexValue(Float opexValue) {
    this.opexValue = opexValue;
  }

  public EcomMarkupPrimaryDTO getEcomMarkupPrimary() {
    return ecomMarkupPrimary;
  }

  public void setEcomMarkupPrimary(EcomMarkupPrimaryDTO ecomMarkupPrimary) {
    this.ecomMarkupPrimary = ecomMarkupPrimary;
  }

  public EcomMarkupSecondaryDTO getEcomMarkupSecondary() {
    return ecomMarkupSecondary;
  }

  public void setEcomMarkupSecondary(EcomMarkupSecondaryDTO ecomMarkupSecondary) {
    this.ecomMarkupSecondary = ecomMarkupSecondary;
  }

  public EcomMarkupTertiaryDTO getEcomMarkupTertiary() {
    return ecomMarkupTertiary;
  }

  public void setEcomMarkupTertiary(EcomMarkupTertiaryDTO ecomMarkupTertiary) {
    this.ecomMarkupTertiary = ecomMarkupTertiary;
  }

  public EcomMarkupQuaternaryDTO getEcomMarkupQuaternary() {
    return ecomMarkupQuaternary;
  }

  public void setEcomMarkupQuaternary(EcomMarkupQuaternaryDTO ecomMarkupQuaternary) {
    this.ecomMarkupQuaternary = ecomMarkupQuaternary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomStoreMarkupDTO)) {
      return false;
    }

    EcomStoreMarkupDTO ecomStoreMarkupDTO = (EcomStoreMarkupDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, ecomStoreMarkupDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomStoreMarkupDTO{" +
            "id=" + getId() +
            ", markupType='" + getMarkupType() + "'" +
            ", minValue=" + getMinValue() +
            ", domesticValue=" + getDomesticValue() +
            ", intlValue=" + getIntlValue() +
            ", flatRateValue=" + getFlatRateValue() +
            ", opexValue=" + getOpexValue() +
            ", ecomMarkupPrimary=" + getEcomMarkupPrimary() +
            ", ecomMarkupSecondary=" + getEcomMarkupSecondary() +
            ", ecomMarkupTertiary=" + getEcomMarkupTertiary() +
            ", ecomMarkupQuaternary=" + getEcomMarkupQuaternary() +
            "}";
    }
}
