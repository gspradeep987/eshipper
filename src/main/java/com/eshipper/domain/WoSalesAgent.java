package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A WoSalesAgent.
 */
@Entity
@Table(name = "wo_sales_agent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WoSalesAgent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne
    @JoinColumn(unique = true)
    private WoSalesAgentDetails woSalesAgentDetails;

    @OneToOne
    @JoinColumn(unique = true)
    private WoSalesCommissionDetails woSalesCommissionDetails;

    @OneToOne
    @JoinColumn(unique = true)
    private WoSalesOperationalDetails woSalesOperationalDetails;

    @OneToOne
    @JoinColumn(unique = true)
    private WoSalesCommissionTransfer woSalesCommissionTransfer;

    @ManyToOne
    @JsonIgnoreProperties(value = "woSalesAgents", allowSetters = true)
    private SalesAgentType salesAgentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public WoSalesAgent isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public WoSalesAgentDetails getWoSalesAgentDetails() {
        return woSalesAgentDetails;
    }

    public WoSalesAgent woSalesAgentDetails(WoSalesAgentDetails woSalesAgentDetails) {
        this.woSalesAgentDetails = woSalesAgentDetails;
        return this;
    }

    public void setWoSalesAgentDetails(WoSalesAgentDetails woSalesAgentDetails) {
        this.woSalesAgentDetails = woSalesAgentDetails;
    }

    public WoSalesCommissionDetails getWoSalesCommissionDetails() {
        return woSalesCommissionDetails;
    }

    public WoSalesAgent woSalesCommissionDetails(WoSalesCommissionDetails woSalesCommissionDetails) {
        this.woSalesCommissionDetails = woSalesCommissionDetails;
        return this;
    }

    public void setWoSalesCommissionDetails(WoSalesCommissionDetails woSalesCommissionDetails) {
        this.woSalesCommissionDetails = woSalesCommissionDetails;
    }

    public WoSalesOperationalDetails getWoSalesOperationalDetails() {
        return woSalesOperationalDetails;
    }

    public WoSalesAgent woSalesOperationalDetails(WoSalesOperationalDetails woSalesOperationalDetails) {
        this.woSalesOperationalDetails = woSalesOperationalDetails;
        return this;
    }

    public void setWoSalesOperationalDetails(WoSalesOperationalDetails woSalesOperationalDetails) {
        this.woSalesOperationalDetails = woSalesOperationalDetails;
    }

    public WoSalesCommissionTransfer getWoSalesCommissionTransfer() {
        return woSalesCommissionTransfer;
    }

    public WoSalesAgent woSalesCommissionTransfer(WoSalesCommissionTransfer woSalesCommissionTransfer) {
        this.woSalesCommissionTransfer = woSalesCommissionTransfer;
        return this;
    }

    public void setWoSalesCommissionTransfer(WoSalesCommissionTransfer woSalesCommissionTransfer) {
        this.woSalesCommissionTransfer = woSalesCommissionTransfer;
    }

    public SalesAgentType getSalesAgentType() {
        return salesAgentType;
    }

    public WoSalesAgent salesAgentType(SalesAgentType salesAgentType) {
        this.salesAgentType = salesAgentType;
        return this;
    }

    public void setSalesAgentType(SalesAgentType salesAgentType) {
        this.salesAgentType = salesAgentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoSalesAgent)) {
            return false;
        }
        return id != null && id.equals(((WoSalesAgent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WoSalesAgent{" +
            "id=" + getId() +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
