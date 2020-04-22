package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A CarrierSettings.
 */
@Entity
@Table(name = "carrier_settings")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CarrierSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "carrierSettings")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<CompanyEcomSettings> companyEcomSettings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<CompanyEcomSettings> getCompanyEcomSettings() {
        return companyEcomSettings;
    }

    public CarrierSettings companyEcomSettings(Set<CompanyEcomSettings> companyEcomSettings) {
        this.companyEcomSettings = companyEcomSettings;
        return this;
    }

    public CarrierSettings addCompanyEcomSettings(CompanyEcomSettings companyEcomSettings) {
        this.companyEcomSettings.add(companyEcomSettings);
        companyEcomSettings.getCarrierSettings().add(this);
        return this;
    }

    public CarrierSettings removeCompanyEcomSettings(CompanyEcomSettings companyEcomSettings) {
        this.companyEcomSettings.remove(companyEcomSettings);
        companyEcomSettings.getCarrierSettings().remove(this);
        return this;
    }

    public void setCompanyEcomSettings(Set<CompanyEcomSettings> companyEcomSettings) {
        this.companyEcomSettings = companyEcomSettings;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarrierSettings)) {
            return false;
        }
        return id != null && id.equals(((CarrierSettings) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CarrierSettings{" +
            "id=" + getId() +
            "}";
    }
}
