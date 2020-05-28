package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A WoSalesCommissionTransfer.
 */
@Entity
@Table(name = "wo_sales_commission_transfer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WoSalesCommissionTransfer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_transfer_date")
    private LocalDate customerTransferDate;

    @Column(name = "is_include_historical_data")
    private Boolean isIncludeHistoricalData;

    @OneToOne(mappedBy = "woSalesCommissionTransfer")
    @JsonIgnore
    private WoSalesAgent woSalesAgent;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCustomerTransferDate() {
        return customerTransferDate;
    }

    public WoSalesCommissionTransfer customerTransferDate(LocalDate customerTransferDate) {
        this.customerTransferDate = customerTransferDate;
        return this;
    }

    public void setCustomerTransferDate(LocalDate customerTransferDate) {
        this.customerTransferDate = customerTransferDate;
    }

    public Boolean isIsIncludeHistoricalData() {
        return isIncludeHistoricalData;
    }

    public WoSalesCommissionTransfer isIncludeHistoricalData(Boolean isIncludeHistoricalData) {
        this.isIncludeHistoricalData = isIncludeHistoricalData;
        return this;
    }

    public void setIsIncludeHistoricalData(Boolean isIncludeHistoricalData) {
        this.isIncludeHistoricalData = isIncludeHistoricalData;
    }

    public WoSalesAgent getWoSalesAgent() {
        return woSalesAgent;
    }

    public WoSalesCommissionTransfer woSalesAgent(WoSalesAgent woSalesAgent) {
        this.woSalesAgent = woSalesAgent;
        return this;
    }

    public void setWoSalesAgent(WoSalesAgent woSalesAgent) {
        this.woSalesAgent = woSalesAgent;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoSalesCommissionTransfer)) {
            return false;
        }
        return id != null && id.equals(((WoSalesCommissionTransfer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WoSalesCommissionTransfer{" +
            "id=" + getId() +
            ", customerTransferDate='" + getCustomerTransferDate() + "'" +
            ", isIncludeHistoricalData='" + isIsIncludeHistoricalData() + "'" +
            "}";
    }
}
