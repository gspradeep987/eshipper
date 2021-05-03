package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.eshipper.domain.EcomWarehouse} entity.
 */
public class EcomWarehouseDTO implements Serializable {

  private Long id;

  @Size(max = 50)
  private String name;

  @Size(max = 50)
  private String address;

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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomWarehouseDTO)) {
      return false;
    }

    EcomWarehouseDTO ecomWarehouseDTO = (EcomWarehouseDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, ecomWarehouseDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomWarehouseDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
