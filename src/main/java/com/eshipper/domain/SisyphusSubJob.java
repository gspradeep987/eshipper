package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SisyphusSubJob.
 */
@Entity
@Table(name = "sisyphus_sub_job")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SisyphusSubJob implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "s_ubjobid")
  private Long sUBJOBID;

  @Column(name = "r_unorder")
  private String rUNORDER;

  @ManyToOne
  @JsonIgnoreProperties(value = { "subJobs" }, allowSetters = true)
  private SisyphusJob sisyphusJob;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SisyphusSubJob id(Long id) {
    this.id = id;
    return this;
  }

  public Long getsUBJOBID() {
    return this.sUBJOBID;
  }

  public SisyphusSubJob sUBJOBID(Long sUBJOBID) {
    this.sUBJOBID = sUBJOBID;
    return this;
  }

  public void setsUBJOBID(Long sUBJOBID) {
    this.sUBJOBID = sUBJOBID;
  }

  public String getrUNORDER() {
    return this.rUNORDER;
  }

  public SisyphusSubJob rUNORDER(String rUNORDER) {
    this.rUNORDER = rUNORDER;
    return this;
  }

  public void setrUNORDER(String rUNORDER) {
    this.rUNORDER = rUNORDER;
  }

  public SisyphusJob getSisyphusJob() {
    return this.sisyphusJob;
  }

  public SisyphusSubJob sisyphusJob(SisyphusJob sisyphusJob) {
    this.setSisyphusJob(sisyphusJob);
    return this;
  }

  public void setSisyphusJob(SisyphusJob sisyphusJob) {
    this.sisyphusJob = sisyphusJob;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SisyphusSubJob)) {
      return false;
    }
    return id != null && id.equals(((SisyphusSubJob) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "SisyphusSubJob{" +
            "id=" + getId() +
            ", sUBJOBID=" + getsUBJOBID() +
            ", rUNORDER='" + getrUNORDER() + "'" +
            "}";
    }
}
