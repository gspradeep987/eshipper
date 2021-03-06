package com.eshipper.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EcomStoreSync.
 */
@Entity
@Table(name = "ecom_store_sync")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EcomStoreSync implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "sync_frequency")
    private Integer syncFrequency;

    @Size(max = 25)
    @Column(name = "sync_interval", length = 25)
    private String syncInterval;

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

    public EcomStoreSync name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSyncFrequency() {
        return syncFrequency;
    }

    public EcomStoreSync syncFrequency(Integer syncFrequency) {
        this.syncFrequency = syncFrequency;
        return this;
    }

    public void setSyncFrequency(Integer syncFrequency) {
        this.syncFrequency = syncFrequency;
    }

    public String getSyncInterval() {
        return syncInterval;
    }

    public EcomStoreSync syncInterval(String syncInterval) {
        this.syncInterval = syncInterval;
        return this;
    }

    public void setSyncInterval(String syncInterval) {
        this.syncInterval = syncInterval;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EcomStoreSync)) {
            return false;
        }
        return id != null && id.equals(((EcomStoreSync) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EcomStoreSync{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", syncFrequency=" + getSyncFrequency() +
            ", syncInterval='" + getSyncInterval() + "'" +
            "}";
    }
}
