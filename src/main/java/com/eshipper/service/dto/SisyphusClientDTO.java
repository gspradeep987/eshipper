package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.SisyphusClient} entity.
 */
public class SisyphusClientDTO implements Serializable {

  private Long id;

  private String name;

  private String contact;

  private String defaultFolder;

  private SisyphusJobDTO sisyphusClient;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getDefaultFolder() {
    return defaultFolder;
  }

  public void setDefaultFolder(String defaultFolder) {
    this.defaultFolder = defaultFolder;
  }

  public SisyphusJobDTO getSisyphusClient() {
    return sisyphusClient;
  }

  public void setSisyphusClient(SisyphusJobDTO sisyphusClient) {
    this.sisyphusClient = sisyphusClient;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SisyphusClientDTO)) {
      return false;
    }

    SisyphusClientDTO sisyphusClientDTO = (SisyphusClientDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, sisyphusClientDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "SisyphusClientDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", contact='" + getContact() + "'" +
            ", defaultFolder='" + getDefaultFolder() + "'" +
            ", sisyphusClient=" + getSisyphusClient() +
            "}";
    }
}
