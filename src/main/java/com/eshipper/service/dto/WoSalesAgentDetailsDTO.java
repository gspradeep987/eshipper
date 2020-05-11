package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.WoSalesAgentDetails} entity.
 */
public class WoSalesAgentDetailsDTO implements Serializable {
    
    private Long id;

    private Long hstNumber;

    private String promoCode;

    private String promoUrl;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHstNumber() {
        return hstNumber;
    }

    public void setHstNumber(Long hstNumber) {
        this.hstNumber = hstNumber;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getPromoUrl() {
        return promoUrl;
    }

    public void setPromoUrl(String promoUrl) {
        this.promoUrl = promoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoSalesAgentDetailsDTO woSalesAgentDetailsDTO = (WoSalesAgentDetailsDTO) o;
        if (woSalesAgentDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woSalesAgentDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoSalesAgentDetailsDTO{" +
            "id=" + getId() +
            ", hstNumber=" + getHstNumber() +
            ", promoCode='" + getPromoCode() + "'" +
            ", promoUrl='" + getPromoUrl() + "'" +
            "}";
    }
}
