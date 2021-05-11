package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

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
    if (!(o instanceof EcomStoreSyncDTO)) {
      return false;
    }

    EcomStoreSyncDTO ecomStoreSyncDTO = (EcomStoreSyncDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, ecomStoreSyncDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
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
