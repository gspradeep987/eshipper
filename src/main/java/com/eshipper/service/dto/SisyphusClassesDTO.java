package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.SisyphusClasses} entity.
 */
public class SisyphusClassesDTO implements Serializable {

  private Long id;

  private Long iD;

  private String nAME;

  private String dESCRIPTION;

  private SisyphusSubJobDTO classes;

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

  public SisyphusSubJobDTO getClasses() {
    return classes;
  }

  public void setClasses(SisyphusSubJobDTO classes) {
    this.classes = classes;
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
            ", iD=" + getiD() +
            ", nAME='" + getnAME() + "'" +
            ", dESCRIPTION='" + getdESCRIPTION() + "'" +
            ", classes=" + getClasses() +
            "}";
    }
}
