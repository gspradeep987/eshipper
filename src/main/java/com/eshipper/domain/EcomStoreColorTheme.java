package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EcomStoreColorTheme.
 */
@Entity
@Table(name = "ecom_store_color_theme")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EcomStoreColorTheme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "jhi_primary", length = 255)
    private String primary;

    @Size(max = 255)
    @Column(name = "secondary", length = 255)
    private String secondary;

    @OneToOne(mappedBy = "ecomStoreColorTheme")
    @JsonIgnore
    private EcomStore ecomStore;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimary() {
        return primary;
    }

    public EcomStoreColorTheme primary(String primary) {
        this.primary = primary;
        return this;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getSecondary() {
        return secondary;
    }

    public EcomStoreColorTheme secondary(String secondary) {
        this.secondary = secondary;
        return this;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public EcomStore getEcomStore() {
        return ecomStore;
    }

    public EcomStoreColorTheme ecomStore(EcomStore ecomStore) {
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
        if (!(o instanceof EcomStoreColorTheme)) {
            return false;
        }
        return id != null && id.equals(((EcomStoreColorTheme) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EcomStoreColorTheme{" +
            "id=" + getId() +
            ", primary='" + getPrimary() + "'" +
            ", secondary='" + getSecondary() + "'" +
            "}";
    }
}
