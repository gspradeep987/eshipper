package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EcomMarkupPrimary.
 */
@Entity
@Table(name = "ecom_markup_primary")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EcomMarkupPrimary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Float value;

    @Size(max = 50)
    @Column(name = "from_lane", length = 50)
    private String fromLane;

    @Size(max = 50)
    @Column(name = "to_lane", length = 50)
    private String toLane;

    @OneToOne(mappedBy = "ecomMarkupPrimary")
    @JsonIgnore
    private EcomStoreMarkup ecomStoreMarkup;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public EcomMarkupPrimary value(Float value) {
        this.value = value;
        return this;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getFromLane() {
        return fromLane;
    }

    public EcomMarkupPrimary fromLane(String fromLane) {
        this.fromLane = fromLane;
        return this;
    }

    public void setFromLane(String fromLane) {
        this.fromLane = fromLane;
    }

    public String getToLane() {
        return toLane;
    }

    public EcomMarkupPrimary toLane(String toLane) {
        this.toLane = toLane;
        return this;
    }

    public void setToLane(String toLane) {
        this.toLane = toLane;
    }

    public EcomStoreMarkup getEcomStoreMarkup() {
        return ecomStoreMarkup;
    }

    public EcomMarkupPrimary ecomStoreMarkup(EcomStoreMarkup ecomStoreMarkup) {
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
        if (!(o instanceof EcomMarkupPrimary)) {
            return false;
        }
        return id != null && id.equals(((EcomMarkupPrimary) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EcomMarkupPrimary{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", fromLane='" + getFromLane() + "'" +
            ", toLane='" + getToLane() + "'" +
            "}";
    }
}
