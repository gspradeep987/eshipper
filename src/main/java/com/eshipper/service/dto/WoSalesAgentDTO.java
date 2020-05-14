package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.WoSalesAgent} entity.
 */
public class WoSalesAgentDTO implements Serializable {
    
    private Long id;

    private Boolean isActive;


    private Long woSalesAgentDetailsId;

    private Long woSalesCommissionDetailsId;

    private Long woSalesOperationalDetailsId;

    private Long woSalesCommissionTransferId;

    private Long salesAgentTypeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Long getWoSalesAgentDetailsId() {
        return woSalesAgentDetailsId;
    }

    public void setWoSalesAgentDetailsId(Long woSalesAgentDetailsId) {
        this.woSalesAgentDetailsId = woSalesAgentDetailsId;
    }

    public Long getWoSalesCommissionDetailsId() {
        return woSalesCommissionDetailsId;
    }

    public void setWoSalesCommissionDetailsId(Long woSalesCommissionDetailsId) {
        this.woSalesCommissionDetailsId = woSalesCommissionDetailsId;
    }

    public Long getWoSalesOperationalDetailsId() {
        return woSalesOperationalDetailsId;
    }

    public void setWoSalesOperationalDetailsId(Long woSalesOperationalDetailsId) {
        this.woSalesOperationalDetailsId = woSalesOperationalDetailsId;
    }

    public Long getWoSalesCommissionTransferId() {
        return woSalesCommissionTransferId;
    }

    public void setWoSalesCommissionTransferId(Long woSalesCommissionTransferId) {
        this.woSalesCommissionTransferId = woSalesCommissionTransferId;
    }

    public Long getSalesAgentTypeId() {
        return salesAgentTypeId;
    }

    public void setSalesAgentTypeId(Long salesAgentTypeId) {
        this.salesAgentTypeId = salesAgentTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoSalesAgentDTO woSalesAgentDTO = (WoSalesAgentDTO) o;
        if (woSalesAgentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woSalesAgentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoSalesAgentDTO{" +
            "id=" + getId() +
            ", isActive='" + isIsActive() + "'" +
            ", woSalesAgentDetailsId=" + getWoSalesAgentDetailsId() +
            ", woSalesCommissionDetailsId=" + getWoSalesCommissionDetailsId() +
            ", woSalesOperationalDetailsId=" + getWoSalesOperationalDetailsId() +
            ", woSalesCommissionTransferId=" + getWoSalesCommissionTransferId() +
            ", salesAgentTypeId=" + getSalesAgentTypeId() +
            "}";
    }
}
