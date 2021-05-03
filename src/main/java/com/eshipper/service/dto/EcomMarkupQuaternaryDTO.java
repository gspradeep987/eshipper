package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.EcomMarkupQuaternary} entity.
 */
public class EcomMarkupQuaternaryDTO implements Serializable {

  private Long id;

  private Float value;

  private CountryDTO country;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Float getValue() {
    return value;
  }

  public void setValue(Float value) {
    this.value = value;
  }

  public CountryDTO getCountry() {
    return country;
  }

  public void setCountry(CountryDTO country) {
    this.country = country;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomMarkupQuaternaryDTO)) {
      return false;
    }

    EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO = (EcomMarkupQuaternaryDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, ecomMarkupQuaternaryDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomMarkupQuaternaryDTO{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", country=" + getCountry() +
            "}";
    }
}
