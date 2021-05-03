package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.Client} entity.
 */
public class ClientDTO implements Serializable {

  private Long id;

  private Long iD;

  private String nAME;

  private String cONTACT;

  private String dEFAULTFOLDER;

  private SisyphusJobDTO client;

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

  public String getcONTACT() {
    return cONTACT;
  }

  public void setcONTACT(String cONTACT) {
    this.cONTACT = cONTACT;
  }

  public String getdEFAULTFOLDER() {
    return dEFAULTFOLDER;
  }

  public void setdEFAULTFOLDER(String dEFAULTFOLDER) {
    this.dEFAULTFOLDER = dEFAULTFOLDER;
  }

  public SisyphusJobDTO getClient() {
    return client;
  }

  public void setClient(SisyphusJobDTO client) {
    this.client = client;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ClientDTO)) {
      return false;
    }

    ClientDTO clientDTO = (ClientDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, clientDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "ClientDTO{" +
            "id=" + getId() +
            ", iD=" + getiD() +
            ", nAME='" + getnAME() + "'" +
            ", cONTACT='" + getcONTACT() + "'" +
            ", dEFAULTFOLDER='" + getdEFAULTFOLDER() + "'" +
            ", client=" + getClient() +
            "}";
    }
}
