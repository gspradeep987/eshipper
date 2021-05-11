package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EcomMarkupSecondary.
 */
@Entity
@Table(name = "ecom_markup_secondary")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EcomMarkupSecondary implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "value")
  private Float value;

  @Size(max = 50)
  @Column(name = "from_zip", length = 50)
  private String fromZip;

  @Size(max = 50)
  @Column(name = "to_zip", length = 50)
  private String toZip;

  @JsonIgnoreProperties(
    value = { "ecomMarkupPrimary", "ecomMarkupSecondary", "ecomMarkupTertiary", "ecomMarkupQuaternary", "ecomStore" },
    allowSetters = true
  )
  @OneToOne(mappedBy = "ecomMarkupSecondary")
  private EcomStoreMarkup ecomStoreMarkup;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EcomMarkupSecondary id(Long id) {
    this.id = id;
    return this;
  }

  public Float getValue() {
    return this.value;
  }

  public EcomMarkupSecondary value(Float value) {
    this.value = value;
    return this;
  }

  public void setValue(Float value) {
    this.value = value;
  }

  public String getFromZip() {
    return this.fromZip;
  }

  public EcomMarkupSecondary fromZip(String fromZip) {
    this.fromZip = fromZip;
    return this;
  }

  public void setFromZip(String fromZip) {
    this.fromZip = fromZip;
  }

  public String getToZip() {
    return this.toZip;
  }

  public EcomMarkupSecondary toZip(String toZip) {
    this.toZip = toZip;
    return this;
  }

  public void setToZip(String toZip) {
    this.toZip = toZip;
  }

  public EcomStoreMarkup getEcomStoreMarkup() {
    return this.ecomStoreMarkup;
  }

  public EcomMarkupSecondary ecomStoreMarkup(EcomStoreMarkup ecomStoreMarkup) {
    this.setEcomStoreMarkup(ecomStoreMarkup);
    return this;
  }

  public void setEcomStoreMarkup(EcomStoreMarkup ecomStoreMarkup) {
    if (this.ecomStoreMarkup != null) {
      this.ecomStoreMarkup.setEcomMarkupSecondary(null);
    }
    if (ecomStoreMarkup != null) {
      ecomStoreMarkup.setEcomMarkupSecondary(this);
    }
    this.ecomStoreMarkup = ecomStoreMarkup;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomMarkupSecondary)) {
      return false;
    }
    return id != null && id.equals(((EcomMarkupSecondary) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomMarkupSecondary{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", fromZip='" + getFromZip() + "'" +
            ", toZip='" + getToZip() + "'" +
            "}";
    }
}
