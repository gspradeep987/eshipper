package com.eshipper.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.WoSalesAgentDetails} entity.
 */
public class WoSalesAgentDetailsDTO implements Serializable {
    
    private Long id;

    private Long hstNumber;

    private String promoCode;

    private String promoUrl;


    private Long paymentMethodId;
    
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

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoSalesAgentDetailsDTO)) {
            return false;
        }

        return id != null && id.equals(((WoSalesAgentDetailsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WoSalesAgentDetailsDTO{" +
            "id=" + getId() +
            ", hstNumber=" + getHstNumber() +
            ", promoCode='" + getPromoCode() + "'" +
            ", promoUrl='" + getPromoUrl() + "'" +
            ", paymentMethodId=" + getPaymentMethodId() +
            "}";
    }
}
