package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.EcomStorePackageSettings} entity.
 */
public class EcomStorePackageSettingsDTO implements Serializable {

  private Long id;

  private Boolean packingInfo1;

  private Boolean packingInfo2;

  private Boolean packingInfo3;

  private Boolean packingInfo4;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Boolean getPackingInfo1() {
    return packingInfo1;
  }

  public void setPackingInfo1(Boolean packingInfo1) {
    this.packingInfo1 = packingInfo1;
  }

  public Boolean getPackingInfo2() {
    return packingInfo2;
  }

  public void setPackingInfo2(Boolean packingInfo2) {
    this.packingInfo2 = packingInfo2;
  }

  public Boolean getPackingInfo3() {
    return packingInfo3;
  }

  public void setPackingInfo3(Boolean packingInfo3) {
    this.packingInfo3 = packingInfo3;
  }

  public Boolean getPackingInfo4() {
    return packingInfo4;
  }

  public void setPackingInfo4(Boolean packingInfo4) {
    this.packingInfo4 = packingInfo4;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomStorePackageSettingsDTO)) {
      return false;
    }

    EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO = (EcomStorePackageSettingsDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, ecomStorePackageSettingsDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomStorePackageSettingsDTO{" +
            "id=" + getId() +
            ", packingInfo1='" + getPackingInfo1() + "'" +
            ", packingInfo2='" + getPackingInfo2() + "'" +
            ", packingInfo3='" + getPackingInfo3() + "'" +
            ", packingInfo4='" + getPackingInfo4() + "'" +
            "}";
    }
}
