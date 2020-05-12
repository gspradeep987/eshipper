package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.WoSalesCommissionCarrier} entity.
 */
public class WoSalesCommissionCarrierDTO implements Serializable {
    
    private Long id;

    private Float commissionPercentageByCarrier;


    private Long carrierId;

    private Long carrierServiceId;

    private Long woSalesCommissionDetailsId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCommissionPercentageByCarrier() {
        return commissionPercentageByCarrier;
    }

    public void setCommissionPercentageByCarrier(Float commissionPercentageByCarrier) {
        this.commissionPercentageByCarrier = commissionPercentageByCarrier;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public Long getCarrierServiceId() {
        return carrierServiceId;
    }

    public void setCarrierServiceId(Long carrierServiceId) {
        this.carrierServiceId = carrierServiceId;
    }

    public Long getWoSalesCommissionDetailsId() {
        return woSalesCommissionDetailsId;
    }

    public void setWoSalesCommissionDetailsId(Long woSalesCommissionDetailsId) {
        this.woSalesCommissionDetailsId = woSalesCommissionDetailsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoSalesCommissionCarrierDTO woSalesCommissionCarrierDTO = (WoSalesCommissionCarrierDTO) o;
        if (woSalesCommissionCarrierDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woSalesCommissionCarrierDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoSalesCommissionCarrierDTO{" +
            "id=" + getId() +
            ", commissionPercentageByCarrier=" + getCommissionPercentageByCarrier() +
            ", carrierId=" + getCarrierId() +
            ", carrierServiceId=" + getCarrierServiceId() +
            ", woSalesCommissionDetailsId=" + getWoSalesCommissionDetailsId() +
            "}";
    }
}
