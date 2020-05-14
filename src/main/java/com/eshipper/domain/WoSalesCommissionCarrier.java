package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A WoSalesCommissionCarrier.
 */
@Entity
@Table(name = "wo_sales_commission_carrier")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WoSalesCommissionCarrier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commission_percentage_by_carrier")
    private Float commissionPercentageByCarrier;

    @ManyToOne
    @JsonIgnoreProperties("woSalesCommissionCarriers")
    private Carrier carrier;

    @ManyToOne
    @JsonIgnoreProperties("woSalesCommissionCarriers")
    private CarrierService carrierService;

    @ManyToOne
    @JsonIgnoreProperties("woSalesCommissionCarriers")
    private WoSalesCommissionDetails woSalesCommissionDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCommissionPercentageByCarrier() {
        return commissionPercentageByCarrier;
    }

    public WoSalesCommissionCarrier commissionPercentageByCarrier(Float commissionPercentageByCarrier) {
        this.commissionPercentageByCarrier = commissionPercentageByCarrier;
        return this;
    }

    public void setCommissionPercentageByCarrier(Float commissionPercentageByCarrier) {
        this.commissionPercentageByCarrier = commissionPercentageByCarrier;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public WoSalesCommissionCarrier carrier(Carrier carrier) {
        this.carrier = carrier;
        return this;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public CarrierService getCarrierService() {
        return carrierService;
    }

    public WoSalesCommissionCarrier carrierService(CarrierService carrierService) {
        this.carrierService = carrierService;
        return this;
    }

    public void setCarrierService(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    public WoSalesCommissionDetails getWoSalesCommissionDetails() {
        return woSalesCommissionDetails;
    }

    public WoSalesCommissionCarrier woSalesCommissionDetails(WoSalesCommissionDetails woSalesCommissionDetails) {
        this.woSalesCommissionDetails = woSalesCommissionDetails;
        return this;
    }

    public void setWoSalesCommissionDetails(WoSalesCommissionDetails woSalesCommissionDetails) {
        this.woSalesCommissionDetails = woSalesCommissionDetails;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoSalesCommissionCarrier)) {
            return false;
        }
        return id != null && id.equals(((WoSalesCommissionCarrier) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoSalesCommissionCarrier{" +
            "id=" + getId() +
            ", commissionPercentageByCarrier=" + getCommissionPercentageByCarrier() +
            "}";
    }
}
