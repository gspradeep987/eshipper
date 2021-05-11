package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.EcomMarkupTertiary} entity.
 */
public class EcomMarkupTertiaryDTO implements Serializable {

  private Long id;

  private Float wgt1to5;

  private Float wgt6to10;

  private Float wgt11to15;

  private Float wgt16;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Float getWgt1to5() {
    return wgt1to5;
  }

  public void setWgt1to5(Float wgt1to5) {
    this.wgt1to5 = wgt1to5;
  }

  public Float getWgt6to10() {
    return wgt6to10;
  }

  public void setWgt6to10(Float wgt6to10) {
    this.wgt6to10 = wgt6to10;
  }

  public Float getWgt11to15() {
    return wgt11to15;
  }

  public void setWgt11to15(Float wgt11to15) {
    this.wgt11to15 = wgt11to15;
  }

  public Float getWgt16() {
    return wgt16;
  }

  public void setWgt16(Float wgt16) {
    this.wgt16 = wgt16;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomMarkupTertiaryDTO)) {
      return false;
    }

    EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO = (EcomMarkupTertiaryDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, ecomMarkupTertiaryDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomMarkupTertiaryDTO{" +
            "id=" + getId() +
            ", wgt1to5=" + getWgt1to5() +
            ", wgt6to10=" + getWgt6to10() +
            ", wgt11to15=" + getWgt11to15() +
            ", wgt16=" + getWgt16() +
            "}";
    }
}
