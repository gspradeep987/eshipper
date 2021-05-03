package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SisyphusJob.
 */
@Entity
@Table(name = "sisyphus_job")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SisyphusJob implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "i_d")
  private Long iD;

  @Column(name = "n_ame")
  private String nAME;

  @Column(name = "s_cheduleminute")
  private String sCHEDULEMINUTE;

  @Column(name = "s_chedulehour")
  private String sCHEDULEHOUR;

  @Column(name = "s_cheduleday")
  private String sCHEDULEDAY;

  @Column(name = "s_chedulemonth")
  private String sCHEDULEMONTH;

  @Column(name = "s_houldrunyn")
  private String sHOULDRUNYN;

  @Column(name = "r_etries")
  private Long rETRIES;

  @Column(name = "m_onitorscheduleyn")
  private Boolean mONITORSCHEDULEYN;

  @OneToMany(mappedBy = "sisyphusJob")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "sisyphusJob" }, allowSetters = true)
  private Set<SisyphusSubJob> subJobs = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SisyphusJob id(Long id) {
    this.id = id;
    return this;
  }

  public Long getiD() {
    return this.iD;
  }

  public SisyphusJob iD(Long iD) {
    this.iD = iD;
    return this;
  }

  public void setiD(Long iD) {
    this.iD = iD;
  }

  public String getnAME() {
    return this.nAME;
  }

  public SisyphusJob nAME(String nAME) {
    this.nAME = nAME;
    return this;
  }

  public void setnAME(String nAME) {
    this.nAME = nAME;
  }

  public String getsCHEDULEMINUTE() {
    return this.sCHEDULEMINUTE;
  }

  public SisyphusJob sCHEDULEMINUTE(String sCHEDULEMINUTE) {
    this.sCHEDULEMINUTE = sCHEDULEMINUTE;
    return this;
  }

  public void setsCHEDULEMINUTE(String sCHEDULEMINUTE) {
    this.sCHEDULEMINUTE = sCHEDULEMINUTE;
  }

  public String getsCHEDULEHOUR() {
    return this.sCHEDULEHOUR;
  }

  public SisyphusJob sCHEDULEHOUR(String sCHEDULEHOUR) {
    this.sCHEDULEHOUR = sCHEDULEHOUR;
    return this;
  }

  public void setsCHEDULEHOUR(String sCHEDULEHOUR) {
    this.sCHEDULEHOUR = sCHEDULEHOUR;
  }

  public String getsCHEDULEDAY() {
    return this.sCHEDULEDAY;
  }

  public SisyphusJob sCHEDULEDAY(String sCHEDULEDAY) {
    this.sCHEDULEDAY = sCHEDULEDAY;
    return this;
  }

  public void setsCHEDULEDAY(String sCHEDULEDAY) {
    this.sCHEDULEDAY = sCHEDULEDAY;
  }

  public String getsCHEDULEMONTH() {
    return this.sCHEDULEMONTH;
  }

  public SisyphusJob sCHEDULEMONTH(String sCHEDULEMONTH) {
    this.sCHEDULEMONTH = sCHEDULEMONTH;
    return this;
  }

  public void setsCHEDULEMONTH(String sCHEDULEMONTH) {
    this.sCHEDULEMONTH = sCHEDULEMONTH;
  }

  public String getsHOULDRUNYN() {
    return this.sHOULDRUNYN;
  }

  public SisyphusJob sHOULDRUNYN(String sHOULDRUNYN) {
    this.sHOULDRUNYN = sHOULDRUNYN;
    return this;
  }

  public void setsHOULDRUNYN(String sHOULDRUNYN) {
    this.sHOULDRUNYN = sHOULDRUNYN;
  }

  public Long getrETRIES() {
    return this.rETRIES;
  }

  public SisyphusJob rETRIES(Long rETRIES) {
    this.rETRIES = rETRIES;
    return this;
  }

  public void setrETRIES(Long rETRIES) {
    this.rETRIES = rETRIES;
  }

  public Boolean getmONITORSCHEDULEYN() {
    return this.mONITORSCHEDULEYN;
  }

  public SisyphusJob mONITORSCHEDULEYN(Boolean mONITORSCHEDULEYN) {
    this.mONITORSCHEDULEYN = mONITORSCHEDULEYN;
    return this;
  }

  public void setmONITORSCHEDULEYN(Boolean mONITORSCHEDULEYN) {
    this.mONITORSCHEDULEYN = mONITORSCHEDULEYN;
  }

  public Set<SisyphusSubJob> getSubJobs() {
    return this.subJobs;
  }

  public SisyphusJob subJobs(Set<SisyphusSubJob> sisyphusSubJobs) {
    this.setSubJobs(sisyphusSubJobs);
    return this;
  }

  public SisyphusJob addSubJobs(SisyphusSubJob sisyphusSubJob) {
    this.subJobs.add(sisyphusSubJob);
    sisyphusSubJob.setSisyphusJob(this);
    return this;
  }

  public SisyphusJob removeSubJobs(SisyphusSubJob sisyphusSubJob) {
    this.subJobs.remove(sisyphusSubJob);
    sisyphusSubJob.setSisyphusJob(null);
    return this;
  }

  public void setSubJobs(Set<SisyphusSubJob> sisyphusSubJobs) {
    if (this.subJobs != null) {
      this.subJobs.forEach(i -> i.setSisyphusJob(null));
    }
    if (sisyphusSubJobs != null) {
      sisyphusSubJobs.forEach(i -> i.setSisyphusJob(this));
    }
    this.subJobs = sisyphusSubJobs;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SisyphusJob)) {
      return false;
    }
    return id != null && id.equals(((SisyphusJob) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "SisyphusJob{" +
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
