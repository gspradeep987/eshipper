package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SisyphusJobType.
 */
@Entity
@Table(name = "sisyphus_job_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SisyphusJobType implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JsonIgnoreProperties(value = { "subJobs", "params" }, allowSetters = true)
  private SisyphusJob sisyphusJobType;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SisyphusJobType id(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return this.name;
  }

  public SisyphusJobType name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public SisyphusJobType description(String description) {
    this.description = description;
    return this;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public SisyphusJob getSisyphusJobType() {
    return this.sisyphusJobType;
  }

  public SisyphusJobType sisyphusJobType(SisyphusJob sisyphusJob) {
    this.setSisyphusJobType(sisyphusJob);
    return this;
  }

  public void setSisyphusJobType(SisyphusJob sisyphusJob) {
    this.sisyphusJobType = sisyphusJob;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SisyphusJobType)) {
      return false;
    }
    return id != null && id.equals(((SisyphusJobType) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "SisyphusJobType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
