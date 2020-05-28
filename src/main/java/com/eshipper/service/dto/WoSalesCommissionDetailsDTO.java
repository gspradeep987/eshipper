package com.eshipper.service.dto;

import java.io.Serializable;

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
        if (!(o instanceof WoSalesCommissionDetailsDTO)) {
            return false;
        }

        return id != null && id.equals(((WoSalesCommissionDetailsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WoSalesCommissionDetailsDTO{" +
            "id=" + getId() +
            ", commission=" + getCommission() +
            "}";
    }
}
