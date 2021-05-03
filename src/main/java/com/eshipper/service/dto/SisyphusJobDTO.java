package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.SisyphusJob} entity.
 */
public class SisyphusJobDTO implements Serializable {

  private Long id;

  private Long iD;

  private String nAME;

  private String sCHEDULEMINUTE;

  private String sCHEDULEHOUR;

  private String sCHEDULEDAY;

  private String sCHEDULEMONTH;

  private String sHOULDRUNYN;

  private Long rETRIES;

  private Boolean mONITORSCHEDULEYN;

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

  public String getsCHEDULEMINUTE() {
    return sCHEDULEMINUTE;
  }

  public void setsCHEDULEMINUTE(String sCHEDULEMINUTE) {
    this.sCHEDULEMINUTE = sCHEDULEMINUTE;
  }

  public String getsCHEDULEHOUR() {
    return sCHEDULEHOUR;
  }

  public void setsCHEDULEHOUR(String sCHEDULEHOUR) {
    this.sCHEDULEHOUR = sCHEDULEHOUR;
  }

  public String getsCHEDULEDAY() {
    return sCHEDULEDAY;
  }

  public void setsCHEDULEDAY(String sCHEDULEDAY) {
    this.sCHEDULEDAY = sCHEDULEDAY;
  }

  public String getsCHEDULEMONTH() {
    return sCHEDULEMONTH;
  }

  public void setsCHEDULEMONTH(String sCHEDULEMONTH) {
    this.sCHEDULEMONTH = sCHEDULEMONTH;
  }

  public String getsHOULDRUNYN() {
    return sHOULDRUNYN;
  }

  public void setsHOULDRUNYN(String sHOULDRUNYN) {
    this.sHOULDRUNYN = sHOULDRUNYN;
  }

  public Long getrETRIES() {
    return rETRIES;
  }

  public void setrETRIES(Long rETRIES) {
    this.rETRIES = rETRIES;
  }

  public Boolean getmONITORSCHEDULEYN() {
    return mONITORSCHEDULEYN;
  }

  public void setmONITORSCHEDULEYN(Boolean mONITORSCHEDULEYN) {
    this.mONITORSCHEDULEYN = mONITORSCHEDULEYN;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SisyphusJobDTO)) {
      return false;
    }

    SisyphusJobDTO sisyphusJobDTO = (SisyphusJobDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, sisyphusJobDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "SisyphusJobDTO{" +
            "id=" + getId() +
            ", iD=" + getiD() +
            ", nAME='" + getnAME() + "'" +
            ", sCHEDULEMINUTE='" + getsCHEDULEMINUTE() + "'" +
            ", sCHEDULEHOUR='" + getsCHEDULEHOUR() + "'" +
            ", sCHEDULEDAY='" + getsCHEDULEDAY() + "'" +
            ", sCHEDULEMONTH='" + getsCHEDULEMONTH() + "'" +
            ", sHOULDRUNYN='" + getsHOULDRUNYN() + "'" +
            ", rETRIES=" + getrETRIES() +
            ", mONITORSCHEDULEYN='" + getmONITORSCHEDULEYN() + "'" +
            "}";
    }
}
