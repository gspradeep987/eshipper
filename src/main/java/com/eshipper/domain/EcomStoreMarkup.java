package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EcomStoreMarkup.
 */
@Entity
@Table(name = "ecom_store_markup")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EcomStoreMarkup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @Column(name = "markup_type", length = 20)
    private String markupType;

    @Column(name = "min_value")
    private Float minValue;

    @Column(name = "domestic_value")
    private Float domesticValue;

    @Column(name = "intl_value")
    private Float intlValue;

    @Column(name = "flat_rate_value")
    private Float flatRateValue;

    @Column(name = "opex_value")
    private Float opexValue;

    @OneToOne
    @JoinColumn(unique = true)
    private EcomMarkupPrimary ecomMarkupPrimary;

    @OneToOne
    @JoinColumn(unique = true)
    private EcomMarkupSecondary ecomMarkupSecondary;

    @OneToOne
    @JoinColumn(unique = true)
    private EcomMarkupTertiary ecomMarkupTertiary;

    @OneToOne
    @JoinColumn(unique = true)
    private EcomMarkupQuaternary ecomMarkupQuaternary;

    @OneToOne(mappedBy = "ecomStoreMarkup")
    @JsonIgnore
    private EcomStore ecomStore;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarkupType() {
        return markupType;
    }

    public EcomStoreMarkup markupType(String markupType) {
        this.markupType = markupType;
        return this;
    }

    public void setMarkupType(String markupType) {
        this.markupType = markupType;
    }

    public Float getMinValue() {
        return minValue;
    }

    public EcomStoreMarkup minValue(Float minValue) {
        this.minValue = minValue;
        return this;
    }

    public void setMinValue(Float minValue) {
        this.minValue = minValue;
    }

    public Float getDomesticValue() {
        return domesticValue;
    }

    public EcomStoreMarkup domesticValue(Float domesticValue) {
        this.domesticValue = domesticValue;
        return this;
    }

    public void setDomesticValue(Float domesticValue) {
        this.domesticValue = domesticValue;
    }

    public Float getIntlValue() {
        return intlValue;
    }

    public EcomStoreMarkup intlValue(Float intlValue) {
        this.intlValue = intlValue;
        return this;
    }

    public void setIntlValue(Float intlValue) {
        this.intlValue = intlValue;
    }

    public Float getFlatRateValue() {
        return flatRateValue;
    }

    public EcomStoreMarkup flatRateValue(Float flatRateValue) {
        this.flatRateValue = flatRateValue;
        return this;
    }

    public void setFlatRateValue(Float flatRateValue) {
        this.flatRateValue = flatRateValue;
    }

    public Float getOpexValue() {
        return opexValue;
    }

    public EcomStoreMarkup opexValue(Float opexValue) {
        this.opexValue = opexValue;
        return this;
    }

    public void setOpexValue(Float opexValue) {
        this.opexValue = opexValue;
    }

    public EcomMarkupPrimary getEcomMarkupPrimary() {
        return ecomMarkupPrimary;
    }

    public EcomStoreMarkup ecomMarkupPrimary(EcomMarkupPrimary ecomMarkupPrimary) {
        this.ecomMarkupPrimary = ecomMarkupPrimary;
        return this;
    }

    public void setEcomMarkupPrimary(EcomMarkupPrimary ecomMarkupPrimary) {
        this.ecomMarkupPrimary = ecomMarkupPrimary;
    }

    public EcomMarkupSecondary getEcomMarkupSecondary() {
        return ecomMarkupSecondary;
    }

    public EcomStoreMarkup ecomMarkupSecondary(EcomMarkupSecondary ecomMarkupSecondary) {
        this.ecomMarkupSecondary = ecomMarkupSecondary;
        return this;
    }

    public void setEcomMarkupSecondary(EcomMarkupSecondary ecomMarkupSecondary) {
        this.ecomMarkupSecondary = ecomMarkupSecondary;
    }

    public EcomMarkupTertiary getEcomMarkupTertiary() {
        return ecomMarkupTertiary;
    }

    public EcomStoreMarkup ecomMarkupTertiary(EcomMarkupTertiary ecomMarkupTertiary) {
        this.ecomMarkupTertiary = ecomMarkupTertiary;
        return this;
    }

    public void setEcomMarkupTertiary(EcomMarkupTertiary ecomMarkupTertiary) {
        this.ecomMarkupTertiary = ecomMarkupTertiary;
    }

    public EcomMarkupQuaternary getEcomMarkupQuaternary() {
        return ecomMarkupQuaternary;
    }

    public EcomStoreMarkup ecomMarkupQuaternary(EcomMarkupQuaternary ecomMarkupQuaternary) {
        this.ecomMarkupQuaternary = ecomMarkupQuaternary;
        return this;
    }

    public void setEcomMarkupQuaternary(EcomMarkupQuaternary ecomMarkupQuaternary) {
        this.ecomMarkupQuaternary = ecomMarkupQuaternary;
    }

    public EcomStore getEcomStore() {
        return ecomStore;
    }

    public EcomStoreMarkup ecomStore(EcomStore ecomStore) {
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
        if (!(o instanceof EcomStoreMarkup)) {
            return false;
        }
        return id != null && id.equals(((EcomStoreMarkup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EcomStoreMarkup{" +
            "id=" + getId() +
            ", markupType='" + getMarkupType() + "'" +
            ", minValue=" + getMinValue() +
            ", domesticValue=" + getDomesticValue() +
            ", intlValue=" + getIntlValue() +
            ", flatRateValue=" + getFlatRateValue() +
            ", opexValue=" + getOpexValue() +
            "}";
    }
}
