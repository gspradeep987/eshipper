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

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JsonIgnoreProperties(value = { "sisyphusJob" }, allowSetters = true)
  private SisyphusSubJob sisyphusClasses;

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

  public String getName() {
    return this.name;
  }

  public SisyphusClasses name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public SisyphusClasses description(String description) {
    this.description = description;
    return this;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public SisyphusSubJob getSisyphusClasses() {
    return this.sisyphusClasses;
  }

  public SisyphusClasses sisyphusClasses(SisyphusSubJob sisyphusSubJob) {
    this.setSisyphusClasses(sisyphusSubJob);
    return this;
  }

  public void setSisyphusClasses(SisyphusSubJob sisyphusSubJob) {
    this.sisyphusClasses = sisyphusSubJob;
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
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
