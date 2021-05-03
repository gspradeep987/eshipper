package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.eshipper.domain.EcomProductImage} entity.
 */
public class EcomProductImageDTO implements Serializable {

  private Long id;

  @Size(max = 100)
  private String imageName;

  @Size(max = 100)
  private String imagePath;

  private EcomProductDTO ecomProduct;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public EcomProductDTO getEcomProduct() {
    return ecomProduct;
  }

  public void setEcomProduct(EcomProductDTO ecomProduct) {
    this.ecomProduct = ecomProduct;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomProductImageDTO)) {
      return false;
    }

    EcomProductImageDTO ecomProductImageDTO = (EcomProductImageDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, ecomProductImageDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomProductImageDTO{" +
            "id=" + getId() +
            ", imageName='" + getImageName() + "'" +
            ", imagePath='" + getImagePath() + "'" +
            ", ecomProduct=" + getEcomProduct() +
            "}";
    }
}
