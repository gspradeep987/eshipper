package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Client implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "i_d")
  private Long iD;

  @Column(name = "n_ame")
  private String nAME;

  @Column(name = "c_ontact")
  private String cONTACT;

  @Column(name = "d_efaultfolder")
  private String dEFAULTFOLDER;

  @ManyToOne
  @JsonIgnoreProperties(value = { "subJobs" }, allowSetters = true)
  private SisyphusJob client;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Client id(Long id) {
    this.id = id;
    return this;
  }

  public Long getiD() {
    return this.iD;
  }

  public Client iD(Long iD) {
    this.iD = iD;
    return this;
  }

  public void setiD(Long iD) {
    this.iD = iD;
  }

  public String getnAME() {
    return this.nAME;
  }

  public Client nAME(String nAME) {
    this.nAME = nAME;
    return this;
  }

  public void setnAME(String nAME) {
    this.nAME = nAME;
  }

  public String getcONTACT() {
    return this.cONTACT;
  }

  public Client cONTACT(String cONTACT) {
    this.cONTACT = cONTACT;
    return this;
  }

  public void setcONTACT(String cONTACT) {
    this.cONTACT = cONTACT;
  }

  public String getdEFAULTFOLDER() {
    return this.dEFAULTFOLDER;
  }

  public Client dEFAULTFOLDER(String dEFAULTFOLDER) {
    this.dEFAULTFOLDER = dEFAULTFOLDER;
    return this;
  }

  public void setdEFAULTFOLDER(String dEFAULTFOLDER) {
    this.dEFAULTFOLDER = dEFAULTFOLDER;
  }

  public SisyphusJob getClient() {
    return this.client;
  }

  public Client client(SisyphusJob sisyphusJob) {
    this.setClient(sisyphusJob);
    return this;
  }

  public void setClient(SisyphusJob sisyphusJob) {
    this.client = sisyphusJob;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Client)) {
      return false;
    }
    return id != null && id.equals(((Client) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", iD=" + getiD() +
            ", nAME='" + getnAME() + "'" +
            ", cONTACT='" + getcONTACT() + "'" +
            ", dEFAULTFOLDER='" + getdEFAULTFOLDER() + "'" +
            "}";
    }
}
