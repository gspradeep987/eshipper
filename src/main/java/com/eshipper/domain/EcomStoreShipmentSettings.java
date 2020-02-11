package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EcomStoreShipmentSettings.
 */
@Entity
@Table(name = "ecom_store_shipment_settings")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EcomStoreShipmentSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @Column(name = "shipping_mode", length = 20)
    private String shippingMode;

    @Size(max = 255)
    @Column(name = "signature_reqd", length = 255)
    private String signatureReqd;

    @Column(name = "schedule_pickup")
    private Boolean schedulePickup;

    @Column(name = "live_rates")
    private Boolean liveRates;

    @Column(name = "add_residential")
    private Boolean addResidential;

    @Column(name = "tailgate_at_destination")
    private Boolean tailgateAtDestination;

    @Column(name = "tailgate_at_source")
    private Boolean tailgateAtSource;

    @OneToOne(mappedBy = "ecomStoreShipmentSettings")
    @JsonIgnore
    private EcomStore ecomStore;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShippingMode() {
        return shippingMode;
    }

    public EcomStoreShipmentSettings shippingMode(String shippingMode) {
        this.shippingMode = shippingMode;
        return this;
    }

    public void setShippingMode(String shippingMode) {
        this.shippingMode = shippingMode;
    }

    public String getSignatureReqd() {
        return signatureReqd;
    }

    public EcomStoreShipmentSettings signatureReqd(String signatureReqd) {
        this.signatureReqd = signatureReqd;
        return this;
    }

    public void setSignatureReqd(String signatureReqd) {
        this.signatureReqd = signatureReqd;
    }

    public Boolean isSchedulePickup() {
        return schedulePickup;
    }

    public EcomStoreShipmentSettings schedulePickup(Boolean schedulePickup) {
        this.schedulePickup = schedulePickup;
        return this;
    }

    public void setSchedulePickup(Boolean schedulePickup) {
        this.schedulePickup = schedulePickup;
    }

    public Boolean isLiveRates() {
        return liveRates;
    }

    public EcomStoreShipmentSettings liveRates(Boolean liveRates) {
        this.liveRates = liveRates;
        return this;
    }

    public void setLiveRates(Boolean liveRates) {
        this.liveRates = liveRates;
    }

    public Boolean isAddResidential() {
        return addResidential;
    }

    public EcomStoreShipmentSettings addResidential(Boolean addResidential) {
        this.addResidential = addResidential;
        return this;
    }

    public void setAddResidential(Boolean addResidential) {
        this.addResidential = addResidential;
    }

    public Boolean isTailgateAtDestination() {
        return tailgateAtDestination;
    }

    public EcomStoreShipmentSettings tailgateAtDestination(Boolean tailgateAtDestination) {
        this.tailgateAtDestination = tailgateAtDestination;
        return this;
    }

    public void setTailgateAtDestination(Boolean tailgateAtDestination) {
        this.tailgateAtDestination = tailgateAtDestination;
    }

    public Boolean isTailgateAtSource() {
        return tailgateAtSource;
    }

    public EcomStoreShipmentSettings tailgateAtSource(Boolean tailgateAtSource) {
        this.tailgateAtSource = tailgateAtSource;
        return this;
    }

    public void setTailgateAtSource(Boolean tailgateAtSource) {
        this.tailgateAtSource = tailgateAtSource;
    }

    public EcomStore getEcomStore() {
        return ecomStore;
    }

    public EcomStoreShipmentSettings ecomStore(EcomStore ecomStore) {
        this.ecomStore = ecomStore;
        return this;
    }

    public void setEcomStore(EcomStore ecomStore) {
        this.ecomStore = ecomStore;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EcomStoreShipmentSettings)) {
            return false;
        }
        return id != null && id.equals(((EcomStoreShipmentSettings) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EcomStoreShipmentSettings{" +
            "id=" + getId() +
            ", shippingMode='" + getShippingMode() + "'" +
            ", signatureReqd='" + getSignatureReqd() + "'" +
            ", schedulePickup='" + isSchedulePickup() + "'" +
            ", liveRates='" + isLiveRates() + "'" +
            ", addResidential='" + isAddResidential() + "'" +
            ", tailgateAtDestination='" + isTailgateAtDestination() + "'" +
            ", tailgateAtSource='" + isTailgateAtSource() + "'" +
            "}";
    }
}
