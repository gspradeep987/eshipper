package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SisyphusJobParam.
 */
@Entity
@Table(name = "sisyphus_job_param")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SisyphusJobParam implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "value")
  private String value;

  @ManyToOne
  @JsonIgnoreProperties(value = { "subJobs", "params" }, allowSetters = true)
  private SisyphusJob sisyphusJob;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SisyphusJobParam id(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return this.name;
  }

  public SisyphusJobParam name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return this.value;
  }

  public SisyphusJobParam value(String value) {
    this.value = value;
    return this;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public SisyphusJob getSisyphusJob() {
    return this.sisyphusJob;
  }

  public SisyphusJobParam sisyphusJob(SisyphusJob sisyphusJob) {
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
    if (!(o instanceof SisyphusJobParam)) {
      return false;
    }
    return id != null && id.equals(((SisyphusJobParam) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "SisyphusJobParam{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
