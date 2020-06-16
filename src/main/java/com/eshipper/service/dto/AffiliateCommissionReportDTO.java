package com.eshipper.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.eshipper.domain.AffiliateCommissionReport} entity.
 */
public class AffiliateCommissionReportDTO implements Serializable {
    
    private Long id;

    private LocalDate cutOffDate;

    private LocalDate createdDate;

    private BigDecimal totalCommissionAmount;

    private BigDecimal pendingCommissionAmount;

    private BigDecimal paidCommissionAmount;

    private Integer noOfShipments;

    private Boolean notifyUser;


    private Long affiliateId;

    private Long currencyId;

    private Long statusId;
    
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

    public BigDecimal getTotalCommissionAmount() {
        return totalCommissionAmount;
    }

    public void setTotalCommissionAmount(BigDecimal totalCommissionAmount) {
        this.totalCommissionAmount = totalCommissionAmount;
    }

    public BigDecimal getPendingCommissionAmount() {
        return pendingCommissionAmount;
    }

    public void setPendingCommissionAmount(BigDecimal pendingCommissionAmount) {
        this.pendingCommissionAmount = pendingCommissionAmount;
    }

    public BigDecimal getPaidCommissionAmount() {
        return paidCommissionAmount;
    }

    public void setPaidCommissionAmount(BigDecimal paidCommissionAmount) {
        this.paidCommissionAmount = paidCommissionAmount;
    }

    public Integer getNoOfShipments() {
        return noOfShipments;
    }

    public void setNoOfShipments(Integer noOfShipments) {
        this.noOfShipments = noOfShipments;
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

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long commissionReportStatusId) {
        this.statusId = commissionReportStatusId;
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
            ", totalCommissionAmount=" + getTotalCommissionAmount() +
            ", pendingCommissionAmount=" + getPendingCommissionAmount() +
            ", paidCommissionAmount=" + getPaidCommissionAmount() +
            ", noOfShipments=" + getNoOfShipments() +
            ", notifyUser='" + isNotifyUser() + "'" +
            ", affiliateId=" + getAffiliateId() +
            ", currencyId=" + getCurrencyId() +
            ", statusId=" + getStatusId() +
            "}";
    }
}
