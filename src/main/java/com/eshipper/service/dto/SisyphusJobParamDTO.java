package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.SisyphusJobParam} entity.
 */
public class SisyphusJobParamDTO implements Serializable {

  private Long id;

  private String name;

  private String value;

  private SisyphusJobDTO sisyphusJob;

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

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public SisyphusJobDTO getSisyphusJob() {
    return sisyphusJob;
  }

  public void setSisyphusJob(SisyphusJobDTO sisyphusJob) {
    this.sisyphusJob = sisyphusJob;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SisyphusJobParamDTO)) {
      return false;
    }

    SisyphusJobParamDTO sisyphusJobParamDTO = (SisyphusJobParamDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, sisyphusJobParamDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "SisyphusJobParamDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            ", sisyphusJob=" + getSisyphusJob() +
            "}";
    }
}
