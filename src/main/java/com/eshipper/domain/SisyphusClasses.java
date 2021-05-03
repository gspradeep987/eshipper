package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SisyphusClasses.
 */
@Entity
@Table(name = "sisyphus_classes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SisyphusClasses implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "i_d")
  private Long iD;

  @Column(name = "n_ame")
  private String nAME;

  @Column(name = "d_escription")
  private String dESCRIPTION;

  @ManyToOne
  @JsonIgnoreProperties(value = { "sisyphusJob" }, allowSetters = true)
  private SisyphusSubJob classes;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SisyphusClasses id(Long id) {
    this.id = id;
    return this;
  }

  public Long getiD() {
    return this.iD;
  }

  public SisyphusClasses iD(Long iD) {
    this.iD = iD;
    return this;
  }

  public void setiD(Long iD) {
    this.iD = iD;
  }

  public String getnAME() {
    return this.nAME;
  }

  public SisyphusClasses nAME(String nAME) {
    this.nAME = nAME;
    return this;
  }

  public void setnAME(String nAME) {
    this.nAME = nAME;
  }

  public String getdESCRIPTION() {
    return this.dESCRIPTION;
  }

  public SisyphusClasses dESCRIPTION(String dESCRIPTION) {
    this.dESCRIPTION = dESCRIPTION;
    return this;
  }

  public void setdESCRIPTION(String dESCRIPTION) {
    this.dESCRIPTION = dESCRIPTION;
  }

  public SisyphusSubJob getClasses() {
    return this.classes;
  }

  public SisyphusClasses classes(SisyphusSubJob sisyphusSubJob) {
    this.setClasses(sisyphusSubJob);
    return this;
  }

  public void setClasses(SisyphusSubJob sisyphusSubJob) {
    this.classes = sisyphusSubJob;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SisyphusClasses)) {
      return false;
    }
    return id != null && id.equals(((SisyphusClasses) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "SisyphusClasses{" +
            "id=" + getId() +
            ", iD=" + getiD() +
            ", nAME='" + getnAME() + "'" +
            ", dESCRIPTION='" + getdESCRIPTION() + "'" +
            "}";
    }
}
