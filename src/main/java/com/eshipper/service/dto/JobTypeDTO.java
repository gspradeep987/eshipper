package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.JobType} entity.
 */
public class JobTypeDTO implements Serializable {

  private Long id;

  private Long iD;

  private String nAME;

  private String dESCRIPTION;

  private SisyphusJobDTO jobType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getiD() {
    return iD;
  }

  public void setiD(Long iD) {
    this.iD = iD;
  }

  public String getnAME() {
    return nAME;
  }

  public void setnAME(String nAME) {
    this.nAME = nAME;
  }

  public String getdESCRIPTION() {
    return dESCRIPTION;
  }

  public void setdESCRIPTION(String dESCRIPTION) {
    this.dESCRIPTION = dESCRIPTION;
  }

  public SisyphusJobDTO getJobType() {
    return jobType;
  }

  public void setJobType(SisyphusJobDTO jobType) {
    this.jobType = jobType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof JobTypeDTO)) {
      return false;
    }

    JobTypeDTO jobTypeDTO = (JobTypeDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, jobTypeDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "JobTypeDTO{" +
            "id=" + getId() +
            ", iD=" + getiD() +
            ", nAME='" + getnAME() + "'" +
            ", dESCRIPTION='" + getdESCRIPTION() + "'" +
            ", jobType=" + getJobType() +
            "}";
    }
}
