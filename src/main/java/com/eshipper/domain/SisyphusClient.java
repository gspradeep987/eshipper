package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SisyphusClient.
 */
@Entity
@Table(name = "sisyphus_client")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SisyphusClient implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "contact")
  private String contact;

  @Column(name = "default_folder")
  private String defaultFolder;

  @ManyToOne
  @JsonIgnoreProperties(value = { "subJobs", "params" }, allowSetters = true)
  private SisyphusJob sisyphusClient;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SisyphusClient id(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return this.name;
  }

  public SisyphusClient name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContact() {
    return this.contact;
  }

  public SisyphusClient contact(String contact) {
    this.contact = contact;
    return this;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getDefaultFolder() {
    return this.defaultFolder;
  }

  public SisyphusClient defaultFolder(String defaultFolder) {
    this.defaultFolder = defaultFolder;
    return this;
  }

  public void setDefaultFolder(String defaultFolder) {
    this.defaultFolder = defaultFolder;
  }

  public SisyphusJob getSisyphusClient() {
    return this.sisyphusClient;
  }

  public SisyphusClient sisyphusClient(SisyphusJob sisyphusJob) {
    this.setSisyphusClient(sisyphusJob);
    return this;
  }

  public void setSisyphusClient(SisyphusJob sisyphusJob) {
    this.sisyphusClient = sisyphusJob;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SisyphusClient)) {
      return false;
    }
    return id != null && id.equals(((SisyphusClient) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "SisyphusClient{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", contact='" + getContact() + "'" +
            ", defaultFolder='" + getDefaultFolder() + "'" +
            "}";
    }
}
