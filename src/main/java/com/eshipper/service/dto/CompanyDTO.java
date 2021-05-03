package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.Company} entity.
 */
public class CompanyDTO implements Serializable {

  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CompanyDTO)) {
      return false;
    }

    CompanyDTO companyDTO = (CompanyDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, companyDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "CompanyDTO{" +
            "id=" + getId() +
            "}";
    }
}
