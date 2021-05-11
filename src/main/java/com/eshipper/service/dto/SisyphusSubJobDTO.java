package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.SisyphusSubJob} entity.
 */
public class SisyphusSubJobDTO implements Serializable {

  private Long id;

  private String runOrder;

  private SisyphusJobDTO sisyphusJob;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRunOrder() {
    return runOrder;
  }

  public void setRunOrder(String runOrder) {
    this.runOrder = runOrder;
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
    if (!(o instanceof SisyphusSubJobDTO)) {
      return false;
    }

    SisyphusSubJobDTO sisyphusSubJobDTO = (SisyphusSubJobDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, sisyphusSubJobDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "SisyphusSubJobDTO{" +
            "id=" + getId() +
            ", runOrder='" + getRunOrder() + "'" +
            ", sisyphusJob=" + getSisyphusJob() +
            "}";
    }
}
