package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.PaymentMethod} entity.
 */
public class PaymentMethodDTO implements Serializable {
    
    private Long id;


    private Long woSalesAgentDetailsId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWoSalesAgentDetailsId() {
        return woSalesAgentDetailsId;
    }

    public void setWoSalesAgentDetailsId(Long woSalesAgentDetailsId) {
        this.woSalesAgentDetailsId = woSalesAgentDetailsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PaymentMethodDTO paymentMethodDTO = (PaymentMethodDTO) o;
        if (paymentMethodDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paymentMethodDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaymentMethodDTO{" +
            "id=" + getId() +
            ", woSalesAgentDetailsId=" + getWoSalesAgentDetailsId() +
            "}";
    }
}
