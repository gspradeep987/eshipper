package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.eshipper.domain.EcomStoreShipmentSettings} entity.
 */
public class EcomStoreShipmentSettingsDTO implements Serializable {

  private Long id;

  @Size(max = 20)
  private String shippingMode;

  @Size(max = 255)
  private String signatureReqd;

  private Boolean schedulePickup;

  private Boolean liveRates;

  private Boolean addResidential;

  private Boolean tailgateAtDestination;

  private Boolean tailgateAtSource;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getShippingMode() {
    return shippingMode;
  }

  public void setShippingMode(String shippingMode) {
    this.shippingMode = shippingMode;
  }

  public String getSignatureReqd() {
    return signatureReqd;
  }

  public void setSignatureReqd(String signatureReqd) {
    this.signatureReqd = signatureReqd;
  }

  public Boolean getSchedulePickup() {
    return schedulePickup;
  }

  public void setSchedulePickup(Boolean schedulePickup) {
    this.schedulePickup = schedulePickup;
  }

  public Boolean getLiveRates() {
    return liveRates;
  }

  public void setLiveRates(Boolean liveRates) {
    this.liveRates = liveRates;
  }

  public Boolean getAddResidential() {
    return addResidential;
  }

  public void setAddResidential(Boolean addResidential) {
    this.addResidential = addResidential;
  }

  public Boolean getTailgateAtDestination() {
    return tailgateAtDestination;
  }

  public void setTailgateAtDestination(Boolean tailgateAtDestination) {
    this.tailgateAtDestination = tailgateAtDestination;
  }

  public Boolean getTailgateAtSource() {
    return tailgateAtSource;
  }

  public void setTailgateAtSource(Boolean tailgateAtSource) {
    this.tailgateAtSource = tailgateAtSource;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomStoreShipmentSettingsDTO)) {
      return false;
    }

    EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO = (EcomStoreShipmentSettingsDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, ecomStoreShipmentSettingsDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomStoreShipmentSettingsDTO{" +
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
