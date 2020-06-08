package com.eshipper.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.AffiliateCommissionReport} entity.
 */
public class AffiliateCommissionReportDTO implements Serializable {
    
    private Long id;

    private LocalDate cutOffDate;

    private LocalDate createdDate;

    private Float totalAmount;

    private Boolean notifyUser;


    private Long affiliateId;

    private Long currencyId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCutOffDate() {
        return cutOffDate;
    }

    public void setCutOffDate(LocalDate cutOffDate) {
        this.cutOffDate = cutOffDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean isNotifyUser() {
        return notifyUser;
    }

    public void setNotifyUser(Boolean notifyUser) {
        this.notifyUser = notifyUser;
    }

    public Long getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(Long affiliateId) {
        this.affiliateId = affiliateId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AffiliateCommissionReportDTO)) {
            return false;
        }

        return id != null && id.equals(((AffiliateCommissionReportDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AffiliateCommissionReportDTO{" +
            "id=" + getId() +
            ", cutOffDate='" + getCutOffDate() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", totalAmount=" + getTotalAmount() +
            ", notifyUser='" + isNotifyUser() + "'" +
            ", affiliateId=" + getAffiliateId() +
            ", currencyId=" + getCurrencyId() +
            "}";
    }
}
