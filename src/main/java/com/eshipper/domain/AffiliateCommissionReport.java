package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "total_amount")
    private Float totalAmount;

    @Column(name = "notify_user")
    private Boolean notifyUser;

    @OneToMany(mappedBy = "affiliateCommissionReport")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ShippingOrder> shipments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "affiliateCommissionReports", allowSetters = true)
    private Affiliate affiliate;

    @ManyToOne
    @JsonIgnoreProperties(value = "affiliateCommissionReports", allowSetters = true)
    private Currency currency;

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

    public Float getTotalAmount() {
        return totalAmount;
    }

    public AffiliateCommissionReport totalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
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

    public Set<ShippingOrder> getShipments() {
        return shipments;
    }

    public AffiliateCommissionReport shipments(Set<ShippingOrder> shippingOrders) {
        this.shipments = shippingOrders;
        return this;
    }

    public AffiliateCommissionReport addShipments(ShippingOrder shippingOrder) {
        this.shipments.add(shippingOrder);
        shippingOrder.setAffiliateCommissionReport(this);
        return this;
    }

    public AffiliateCommissionReport removeShipments(ShippingOrder shippingOrder) {
        this.shipments.remove(shippingOrder);
        shippingOrder.setAffiliateCommissionReport(null);
        return this;
    }

    public void setShipments(Set<ShippingOrder> shippingOrders) {
        this.shipments = shippingOrders;
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
            ", totalAmount=" + getTotalAmount() +
            ", notifyUser='" + isNotifyUser() + "'" +
            "}";
    }
}
