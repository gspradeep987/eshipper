package com.eshipper.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.WoSalesCommissionTransfer} entity.
 */
public class WoSalesCommissionTransferDTO implements Serializable {
    
    private Long id;

    private LocalDate customerTransferDate;

    private Boolean isIncludeHistoricalData;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCustomerTransferDate() {
        return customerTransferDate;
    }

    public void setCustomerTransferDate(LocalDate customerTransferDate) {
        this.customerTransferDate = customerTransferDate;
    }

    public Boolean isIsIncludeHistoricalData() {
        return isIncludeHistoricalData;
    }

    public void setIsIncludeHistoricalData(Boolean isIncludeHistoricalData) {
        this.isIncludeHistoricalData = isIncludeHistoricalData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoSalesCommissionTransferDTO)) {
            return false;
        }

        return id != null && id.equals(((WoSalesCommissionTransferDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WoSalesCommissionTransferDTO{" +
            "id=" + getId() +
            ", customerTransferDate='" + getCustomerTransferDate() + "'" +
            ", isIncludeHistoricalData='" + isIsIncludeHistoricalData() + "'" +
            "}";
    }
}
