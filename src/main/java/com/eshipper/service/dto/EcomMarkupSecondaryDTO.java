package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.eshipper.domain.EcomMarkupSecondary} entity.
 */
public class EcomMarkupSecondaryDTO implements Serializable {

  private Long id;

  private Float value;

  @Size(max = 50)
  private String fromZip;

  @Size(max = 50)
  private String toZip;

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

  public String getFromZip() {
    return fromZip;
  }

  public void setFromZip(String fromZip) {
    this.fromZip = fromZip;
  }

  public String getToZip() {
    return toZip;
  }

  public void setToZip(String toZip) {
    this.toZip = toZip;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomMarkupSecondaryDTO)) {
      return false;
    }

    EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO = (EcomMarkupSecondaryDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, ecomMarkupSecondaryDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomMarkupSecondaryDTO{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", fromZip='" + getFromZip() + "'" +
            ", toZip='" + getToZip() + "'" +
            "}";
    }
}
