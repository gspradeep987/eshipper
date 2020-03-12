package com.eshipper.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.EcomStoreSync} entity.
 */
public class EcomStoreSyncDTO implements Serializable {
    
    private Long id;

    @Size(max = 255)
    private String name;

    private Integer syncFrequency;

    @Size(max = 25)
    private String syncInterval;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSyncFrequency() {
        return syncFrequency;
    }

    public void setSyncFrequency(Integer syncFrequency) {
        this.syncFrequency = syncFrequency;
    }

    public String getSyncInterval() {
        return syncInterval;
    }

    public void setSyncInterval(String syncInterval) {
        this.syncInterval = syncInterval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EcomStoreSyncDTO ecomStoreSyncDTO = (EcomStoreSyncDTO) o;
        if (ecomStoreSyncDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ecomStoreSyncDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EcomStoreSyncDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", syncFrequency=" + getSyncFrequency() +
            ", syncInterval='" + getSyncInterval() + "'" +
            "}";
    }
}
