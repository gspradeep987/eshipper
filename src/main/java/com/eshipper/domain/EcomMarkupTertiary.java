package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A EcomMarkupTertiary.
 */
@Entity
@Table(name = "ecom_markup_tertiary")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EcomMarkupTertiary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wgt_1_to_5")
    private Float wgt1to5;

    @Column(name = "wgt_6_to_10")
    private Float wgt6to10;

    @Column(name = "wgt_11_to_15")
    private Float wgt11to15;

    @Column(name = "wgt_16")
    private Float wgt16;

    @OneToOne(mappedBy = "ecomMarkupTertiary")
    @JsonIgnore
    private EcomStoreMarkup ecomStoreMarkup;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getWgt1to5() {
        return wgt1to5;
    }

    public EcomMarkupTertiary wgt1to5(Float wgt1to5) {
        this.wgt1to5 = wgt1to5;
        return this;
    }

    public void setWgt1to5(Float wgt1to5) {
        this.wgt1to5 = wgt1to5;
    }

    public Float getWgt6to10() {
        return wgt6to10;
    }

    public EcomMarkupTertiary wgt6to10(Float wgt6to10) {
        this.wgt6to10 = wgt6to10;
        return this;
    }

    public void setWgt6to10(Float wgt6to10) {
        this.wgt6to10 = wgt6to10;
    }

    public Float getWgt11to15() {
        return wgt11to15;
    }

    public EcomMarkupTertiary wgt11to15(Float wgt11to15) {
        this.wgt11to15 = wgt11to15;
        return this;
    }

    public void setWgt11to15(Float wgt11to15) {
        this.wgt11to15 = wgt11to15;
    }

    public Float getWgt16() {
        return wgt16;
    }

    public EcomMarkupTertiary wgt16(Float wgt16) {
        this.wgt16 = wgt16;
        return this;
    }

    public void setWgt16(Float wgt16) {
        this.wgt16 = wgt16;
    }

    public EcomStoreMarkup getEcomStoreMarkup() {
        return ecomStoreMarkup;
    }

    public EcomMarkupTertiary ecomStoreMarkup(EcomStoreMarkup ecomStoreMarkup) {
        this.ecomStoreMarkup = ecomStoreMarkup;
        return this;
    }

    public void setEcomStoreMarkup(EcomStoreMarkup ecomStoreMarkup) {
        this.ecomStoreMarkup = ecomStoreMarkup;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EcomMarkupTertiary)) {
            return false;
        }
        return id != null && id.equals(((EcomMarkupTertiary) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EcomMarkupTertiary{" +
            "id=" + getId() +
            ", wgt1to5=" + getWgt1to5() +
            ", wgt6to10=" + getWgt6to10() +
            ", wgt11to15=" + getWgt11to15() +
            ", wgt16=" + getWgt16() +
            "}";
    }
}
