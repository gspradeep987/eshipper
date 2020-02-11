package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A EcomMarkupQuaternary.
 */
@Entity
@Table(name = "ecom_markup_quaternary")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EcomMarkupQuaternary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Float value;

    @ManyToOne
    @JsonIgnoreProperties("ecomMarkupQuaternaries")
    private Country country;

    @OneToOne(mappedBy = "ecomMarkupQuaternary")
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

    public EcomMarkupQuaternary value(Float value) {
        this.value = value;
        return this;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Country getCountry() {
        return country;
    }

    public EcomMarkupQuaternary country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public EcomStoreMarkup getEcomStoreMarkup() {
        return ecomStoreMarkup;
    }

    public EcomMarkupQuaternary ecomStoreMarkup(EcomStoreMarkup ecomStoreMarkup) {
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
        if (!(o instanceof EcomMarkupQuaternary)) {
            return false;
        }
        return id != null && id.equals(((EcomMarkupQuaternary) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EcomMarkupQuaternary{" +
            "id=" + getId() +
            ", value=" + getValue() +
            "}";
    }
}
