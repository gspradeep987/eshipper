package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.SisyphusJobType} entity.
 */
public class SisyphusJobTypeDTO implements Serializable {

  private Long id;

  private String name;

  private String description;

  private SisyphusJobDTO sisyphusJobType;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public SisyphusJobDTO getSisyphusJobType() {
    return sisyphusJobType;
  }

  public void setSisyphusJobType(SisyphusJobDTO sisyphusJobType) {
    this.sisyphusJobType = sisyphusJobType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SisyphusJobTypeDTO)) {
      return false;
    }

    SisyphusJobTypeDTO sisyphusJobTypeDTO = (SisyphusJobTypeDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, sisyphusJobTypeDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "SisyphusJobTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", sisyphusJobType=" + getSisyphusJobType() +
            "}";
    }
}
