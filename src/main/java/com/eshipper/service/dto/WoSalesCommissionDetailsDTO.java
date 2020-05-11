package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.WoSalesCommissionDetails} entity.
 */
public class WoSalesCommissionDetailsDTO implements Serializable {
    
    private Long id;

    private Float commission;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCommission() {
        return commission;
    }

    public void setCommission(Float commission) {
        this.commission = commission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoSalesCommissionDetailsDTO woSalesCommissionDetailsDTO = (WoSalesCommissionDetailsDTO) o;
        if (woSalesCommissionDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woSalesCommissionDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoSalesCommissionDetailsDTO{" +
            "id=" + getId() +
            ", commission=" + getCommission() +
            "}";
    }
}
