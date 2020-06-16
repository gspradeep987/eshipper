package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A AffiliateCommissionReport.
 */
@Entity
@Table(name = "affiliate_commission_report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AffiliateCommissionReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cut_off_date")
    private LocalDate cutOffDate;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "total_commission_amount", precision = 21, scale = 2)
    private BigDecimal totalCommissionAmount;

    @Column(name = "pending_commission_amount", precision = 21, scale = 2)
    private BigDecimal pendingCommissionAmount;

    @Column(name = "paid_commission_amount", precision = 21, scale = 2)
    private BigDecimal paidCommissionAmount;

    @Column(name = "no_of_shipments")
    private Integer noOfShipments;

    @Column(name = "notify_user")
    private Boolean notifyUser;

    @ManyToOne
    @JsonIgnoreProperties(value = "affiliateCommissionReports", allowSetters = true)
    private Affiliate affiliate;

    @ManyToOne
    @JsonIgnoreProperties(value = "affiliateCommissionReports", allowSetters = true)
    private Currency currency;

    @ManyToOne
    @JsonIgnoreProperties(value = "affiliateCommissionReports", allowSetters = true)
    private CommissionReportStatus status;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCutOffDate() {
        return cutOffDate;
    }

    public AffiliateCommissionReport cutOffDate(LocalDate cutOffDate) {
        this.cutOffDate = cutOffDate;
        return this;
    }

    public void setCutOffDate(LocalDate cutOffDate) {
        this.cutOffDate = cutOffDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public AffiliateCommissionReport createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public BigDecimal getTotalCommissionAmount() {
        return totalCommissionAmount;
    }

    public AffiliateCommissionReport totalCommissionAmount(BigDecimal totalCommissionAmount) {
        this.totalCommissionAmount = totalCommissionAmount;
        return this;
    }

    public void setTotalCommissionAmount(BigDecimal totalCommissionAmount) {
        this.totalCommissionAmount = totalCommissionAmount;
    }

    public BigDecimal getPendingCommissionAmount() {
        return pendingCommissionAmount;
    }

    public AffiliateCommissionReport pendingCommissionAmount(BigDecimal pendingCommissionAmount) {
        this.pendingCommissionAmount = pendingCommissionAmount;
        return this;
    }

    public void setPendingCommissionAmount(BigDecimal pendingCommissionAmount) {
        this.pendingCommissionAmount = pendingCommissionAmount;
    }

    public BigDecimal getPaidCommissionAmount() {
        return paidCommissionAmount;
    }

    public AffiliateCommissionReport paidCommissionAmount(BigDecimal paidCommissionAmount) {
        this.paidCommissionAmount = paidCommissionAmount;
        return this;
    }

    public void setPaidCommissionAmount(BigDecimal paidCommissionAmount) {
        this.paidCommissionAmount = paidCommissionAmount;
    }

    public Integer getNoOfShipments() {
        return noOfShipments;
    }

    public AffiliateCommissionReport noOfShipments(Integer noOfShipments) {
        this.noOfShipments = noOfShipments;
        return this;
    }

    public void setNoOfShipments(Integer noOfShipments) {
        this.noOfShipments = noOfShipments;
    }

    public Boolean isNotifyUser() {
        return notifyUser;
    }

    public AffiliateCommissionReport notifyUser(Boolean notifyUser) {
        this.notifyUser = notifyUser;
        return this;
    }

    public void setNotifyUser(Boolean notifyUser) {
        this.notifyUser = notifyUser;
    }

    public Affiliate getAffiliate() {
        return affiliate;
    }

    public AffiliateCommissionReport affiliate(Affiliate affiliate) {
        this.affiliate = affiliate;
        return this;
    }

    public void setAffiliate(Affiliate affiliate) {
        this.affiliate = affiliate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public AffiliateCommissionReport currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public CommissionReportStatus getStatus() {
        return status;
    }

    public AffiliateCommissionReport status(CommissionReportStatus commissionReportStatus) {
        this.status = commissionReportStatus;
        return this;
    }

    public void setStatus(CommissionReportStatus commissionReportStatus) {
        this.status = commissionReportStatus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AffiliateCommissionReport)) {
            return false;
        }
        return id != null && id.equals(((AffiliateCommissionReport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AffiliateCommissionReport{" +
            "id=" + getId() +
            ", cutOffDate='" + getCutOffDate() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", totalCommissionAmount=" + getTotalCommissionAmount() +
            ", pendingCommissionAmount=" + getPendingCommissionAmount() +
            ", paidCommissionAmount=" + getPaidCommissionAmount() +
            ", noOfShipments=" + getNoOfShipments() +
            ", notifyUser='" + isNotifyUser() + "'" +
            "}";
    }
}
