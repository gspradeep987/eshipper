package com.eshipper.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

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

    public Boolean isSchedulePickup() {
        return schedulePickup;
    }

    public void setSchedulePickup(Boolean schedulePickup) {
        this.schedulePickup = schedulePickup;
    }

    public Boolean isLiveRates() {
        return liveRates;
    }

    public void setLiveRates(Boolean liveRates) {
        this.liveRates = liveRates;
    }

    public Boolean isAddResidential() {
        return addResidential;
    }

    public void setAddResidential(Boolean addResidential) {
        this.addResidential = addResidential;
    }

    public Boolean isTailgateAtDestination() {
        return tailgateAtDestination;
    }

    public void setTailgateAtDestination(Boolean tailgateAtDestination) {
        this.tailgateAtDestination = tailgateAtDestination;
    }

    public Boolean isTailgateAtSource() {
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO = (EcomStoreShipmentSettingsDTO) o;
        if (ecomStoreShipmentSettingsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ecomStoreShipmentSettingsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EcomStoreShipmentSettingsDTO{" +
            "id=" + getId() +
            ", shippingMode='" + getShippingMode() + "'" +
            ", signatureReqd='" + getSignatureReqd() + "'" +
            ", schedulePickup='" + isSchedulePickup() + "'" +
            ", liveRates='" + isLiveRates() + "'" +
            ", addResidential='" + isAddResidential() + "'" +
            ", tailgateAtDestination='" + isTailgateAtDestination() + "'" +
            ", tailgateAtSource='" + isTailgateAtSource() + "'" +
            "}";
    }
}
