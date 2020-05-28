package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A WoSalesOperationalCarrier.
 */
@Entity
@Table(name = "wo_sales_operational_carrier")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WoSalesOperationalCarrier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "op_exp")
    private Float opExp;

    @ManyToOne
    @JsonIgnoreProperties(value = "woSalesOperationalCarriers", allowSetters = true)
    private Carrier carrier;

    @ManyToOne
    @JsonIgnoreProperties(value = "woSalesOperationalCarriers", allowSetters = true)
    private CarrierService carrierService;

    @ManyToOne
    @JsonIgnoreProperties(value = "woSalesOperationalCarriers", allowSetters = true)
    private WoSalesOperationalDetails woSalesOperationalDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getOpExp() {
        return opExp;
    }

    public WoSalesOperationalCarrier opExp(Float opExp) {
        this.opExp = opExp;
        return this;
    }

    public void setOpExp(Float opExp) {
        this.opExp = opExp;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public WoSalesOperationalCarrier carrier(Carrier carrier) {
        this.carrier = carrier;
        return this;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public CarrierService getCarrierService() {
        return carrierService;
    }

    public WoSalesOperationalCarrier carrierService(CarrierService carrierService) {
        this.carrierService = carrierService;
        return this;
    }

    public void setCarrierService(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    public WoSalesOperationalDetails getWoSalesOperationalDetails() {
        return woSalesOperationalDetails;
    }

    public WoSalesOperationalCarrier woSalesOperationalDetails(WoSalesOperationalDetails woSalesOperationalDetails) {
        this.woSalesOperationalDetails = woSalesOperationalDetails;
        return this;
    }

    public void setWoSalesOperationalDetails(WoSalesOperationalDetails woSalesOperationalDetails) {
        this.woSalesOperationalDetails = woSalesOperationalDetails;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoSalesOperationalCarrier)) {
            return false;
        }
        return id != null && id.equals(((WoSalesOperationalCarrier) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WoSalesOperationalCarrier{" +
            "id=" + getId() +
            ", opExp=" + getOpExp() +
            "}";
    }
}
