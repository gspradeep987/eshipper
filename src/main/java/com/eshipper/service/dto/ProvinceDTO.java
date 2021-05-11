package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.Province} entity.
 */
public class ProvinceDTO implements Serializable {

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
    if (!(o instanceof ProvinceDTO)) {
      return false;
    }

    ProvinceDTO provinceDTO = (ProvinceDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, provinceDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "ProvinceDTO{" +
            "id=" + getId() +
            "}";
    }
}
