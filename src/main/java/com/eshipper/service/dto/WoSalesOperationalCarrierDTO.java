package com.eshipper.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.WoSalesOperationalCarrier} entity.
 */
public class WoSalesOperationalCarrierDTO implements Serializable {
    
    private Long id;

    private Float opExp;


    private Long carrierId;

    private Long carrierServiceId;

    private Long woSalesOperationalDetailsId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getOpExp() {
        return opExp;
    }

    public void setOpExp(Float opExp) {
        this.opExp = opExp;
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

    public Long getWoSalesOperationalDetailsId() {
        return woSalesOperationalDetailsId;
    }

    public void setWoSalesOperationalDetailsId(Long woSalesOperationalDetailsId) {
        this.woSalesOperationalDetailsId = woSalesOperationalDetailsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoSalesOperationalCarrierDTO)) {
            return false;
        }

        return id != null && id.equals(((WoSalesOperationalCarrierDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WoSalesOperationalCarrierDTO{" +
            "id=" + getId() +
            ", opExp=" + getOpExp() +
            ", carrierId=" + getCarrierId() +
            ", carrierServiceId=" + getCarrierServiceId() +
            ", woSalesOperationalDetailsId=" + getWoSalesOperationalDetailsId() +
            "}";
    }
}
