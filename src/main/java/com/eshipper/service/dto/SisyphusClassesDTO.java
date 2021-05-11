package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.SisyphusClasses} entity.
 */
public class SisyphusClassesDTO implements Serializable {

  private Long id;

  private String name;

  private String description;

  private SisyphusSubJobDTO sisyphusClasses;

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

  public SisyphusSubJobDTO getSisyphusClasses() {
    return sisyphusClasses;
  }

  public void setSisyphusClasses(SisyphusSubJobDTO sisyphusClasses) {
    this.sisyphusClasses = sisyphusClasses;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SisyphusClassesDTO)) {
      return false;
    }

    SisyphusClassesDTO sisyphusClassesDTO = (SisyphusClassesDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, sisyphusClassesDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "SisyphusClassesDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", sisyphusClasses=" + getSisyphusClasses() +
            "}";
    }
}
