package com.eshipper.service.dto;

import java.io.Serializable;

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
        if (!(o instanceof WoSalesCommissionCarrierDTO)) {
            return false;
        }

        return id != null && id.equals(((WoSalesCommissionCarrierDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
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
