package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A WoSalesCommissionDetails.
 */
@Entity
@Table(name = "wo_sales_commission_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WoSalesCommissionDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commission")
    private Float commission;

    @OneToMany(mappedBy = "woSalesCommissionDetails")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WoSalesCommissionCarrier> woSalesCommissionCarriers = new HashSet<>();

    @OneToOne(mappedBy = "woSalesCommissionDetails")
    @JsonIgnore
    private WoSalesAgent woSalesAgent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCommission() {
        return commission;
    }

    public WoSalesCommissionDetails commission(Float commission) {
        this.commission = commission;
        return this;
    }

    public void setCommission(Float commission) {
        this.commission = commission;
    }

    public Set<WoSalesCommissionCarrier> getWoSalesCommissionCarriers() {
        return woSalesCommissionCarriers;
    }

    public WoSalesCommissionDetails woSalesCommissionCarriers(Set<WoSalesCommissionCarrier> woSalesCommissionCarriers) {
        this.woSalesCommissionCarriers = woSalesCommissionCarriers;
        return this;
    }

    public WoSalesCommissionDetails addWoSalesCommissionCarrier(WoSalesCommissionCarrier woSalesCommissionCarrier) {
        this.woSalesCommissionCarriers.add(woSalesCommissionCarrier);
        woSalesCommissionCarrier.setWoSalesCommissionDetails(this);
        return this;
    }

    public WoSalesCommissionDetails removeWoSalesCommissionCarrier(WoSalesCommissionCarrier woSalesCommissionCarrier) {
        this.woSalesCommissionCarriers.remove(woSalesCommissionCarrier);
        woSalesCommissionCarrier.setWoSalesCommissionDetails(null);
        return this;
    }

    public void setWoSalesCommissionCarriers(Set<WoSalesCommissionCarrier> woSalesCommissionCarriers) {
        this.woSalesCommissionCarriers = woSalesCommissionCarriers;
    }

    public WoSalesAgent getWoSalesAgent() {
        return woSalesAgent;
    }

    public WoSalesCommissionDetails woSalesAgent(WoSalesAgent woSalesAgent) {
        this.woSalesAgent = woSalesAgent;
        return this;
    }

    public void setWoSalesAgent(WoSalesAgent woSalesAgent) {
        this.woSalesAgent = woSalesAgent;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoSalesCommissionDetails)) {
            return false;
        }
        return id != null && id.equals(((WoSalesCommissionDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoSalesCommissionDetails{" +
            "id=" + getId() +
            ", commission=" + getCommission() +
            "}";
    }
}
