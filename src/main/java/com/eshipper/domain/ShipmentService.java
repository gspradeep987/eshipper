package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ShipmentService.
 */
@Entity
@Table(name = "shipment_service")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ShipmentService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @ManyToMany(mappedBy = "shipmentServices")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<EcomStore> ecomStores = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ShipmentService name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EcomStore> getEcomStores() {
        return ecomStores;
    }

    public ShipmentService ecomStores(Set<EcomStore> ecomStores) {
        this.ecomStores = ecomStores;
        return this;
    }

    public ShipmentService addEcomStore(EcomStore ecomStore) {
        this.ecomStores.add(ecomStore);
        ecomStore.getShipmentServices().add(this);
        return this;
    }

    public ShipmentService removeEcomStore(EcomStore ecomStore) {
        this.ecomStores.remove(ecomStore);
        ecomStore.getShipmentServices().remove(this);
        return this;
    }

    public void setEcomStores(Set<EcomStore> ecomStores) {
        this.ecomStores = ecomStores;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShipmentService)) {
            return false;
        }
        return id != null && id.equals(((ShipmentService) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ShipmentService{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
