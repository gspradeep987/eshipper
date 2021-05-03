package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EcomProductImage.
 */
@Entity
@Table(name = "ecom_product_image")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EcomProductImage implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 100)
  @Column(name = "image_name", length = 100)
  private String imageName;

  @Size(max = 100)
  @Column(name = "image_path", length = 100)
  private String imagePath;

  @ManyToOne
  @JsonIgnoreProperties(value = { "ecomProductImages", "country", "ecomWarehouses", "ecomOrder" }, allowSetters = true)
  private EcomProduct ecomProduct;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EcomProductImage id(Long id) {
    this.id = id;
    return this;
  }

  public String getImageName() {
    return this.imageName;
  }

  public EcomProductImage imageName(String imageName) {
    this.imageName = imageName;
    return this;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public String getImagePath() {
    return this.imagePath;
  }

  public EcomProductImage imagePath(String imagePath) {
    this.imagePath = imagePath;
    return this;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public EcomProduct getEcomProduct() {
    return this.ecomProduct;
  }

  public EcomProductImage ecomProduct(EcomProduct ecomProduct) {
    this.setEcomProduct(ecomProduct);
    return this;
  }

  public void setEcomProduct(EcomProduct ecomProduct) {
    this.ecomProduct = ecomProduct;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomProductImage)) {
      return false;
    }
    return id != null && id.equals(((EcomProductImage) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomProductImage{" +
            "id=" + getId() +
            ", imageName='" + getImageName() + "'" +
            ", imagePath='" + getImagePath() + "'" +
            "}";
    }
}
