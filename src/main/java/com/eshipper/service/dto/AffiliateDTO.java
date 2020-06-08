package com.eshipper.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.Affiliate} entity.
 */
public class AffiliateDTO implements Serializable {
    
    private Long id;

    private Boolean isActive;

    private Boolean notifyUser;

    private String promoCode;

    private String promoCodeUrl;

    private Float commissionPercentage;

    private LocalDate commissionDate;

    private LocalDate createdDate;

    private LocalDate updatedDate;


    private Long paymentMethodId;

    private Long affiliateId;

    private Long franchiseId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean isNotifyUser() {
        return notifyUser;
    }

    public void setNotifyUser(Boolean notifyUser) {
        this.notifyUser = notifyUser;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getPromoCodeUrl() {
        return promoCodeUrl;
    }

    public void setPromoCodeUrl(String promoCodeUrl) {
        this.promoCodeUrl = promoCodeUrl;
    }

    public Float getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(Float commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public LocalDate getCommissionDate() {
        return commissionDate;
    }

    public void setCommissionDate(LocalDate commissionDate) {
        this.commissionDate = commissionDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Long getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(Long companyId) {
        this.affiliateId = companyId;
    }

    public Long getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(Long franchiseId) {
        this.franchiseId = franchiseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AffiliateDTO)) {
            return false;
        }

        return id != null && id.equals(((AffiliateDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AffiliateDTO{" +
            "id=" + getId() +
            ", isActive='" + isIsActive() + "'" +
            ", notifyUser='" + isNotifyUser() + "'" +
            ", promoCode='" + getPromoCode() + "'" +
            ", promoCodeUrl='" + getPromoCodeUrl() + "'" +
            ", commissionPercentage=" + getCommissionPercentage() +
            ", commissionDate='" + getCommissionDate() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", paymentMethodId=" + getPaymentMethodId() +
            ", affiliateId=" + getAffiliateId() +
            ", franchiseId=" + getFranchiseId() +
            "}";
    }
}
