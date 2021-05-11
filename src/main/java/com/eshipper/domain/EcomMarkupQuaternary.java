package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EcomMarkupQuaternary.
 */
@Entity
@Table(name = "ecom_markup_quaternary")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EcomMarkupQuaternary implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "value")
  private Float value;

  @ManyToOne
  private Country country;

  @JsonIgnoreProperties(
    value = { "ecomMarkupPrimary", "ecomMarkupSecondary", "ecomMarkupTertiary", "ecomMarkupQuaternary", "ecomStore" },
    allowSetters = true
  )
  @OneToOne(mappedBy = "ecomMarkupQuaternary")
  private EcomStoreMarkup ecomStoreMarkup;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EcomMarkupQuaternary id(Long id) {
    this.id = id;
    return this;
  }

  public Float getValue() {
    return this.value;
  }

  public EcomMarkupQuaternary value(Float value) {
    this.value = value;
    return this;
  }

  public void setValue(Float value) {
    this.value = value;
  }

  public Country getCountry() {
    return this.country;
  }

  public EcomMarkupQuaternary country(Country country) {
    this.setCountry(country);
    return this;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public EcomStoreMarkup getEcomStoreMarkup() {
    return this.ecomStoreMarkup;
  }

  public EcomMarkupQuaternary ecomStoreMarkup(EcomStoreMarkup ecomStoreMarkup) {
    this.setEcomStoreMarkup(ecomStoreMarkup);
    return this;
  }

  public void setEcomStoreMarkup(EcomStoreMarkup ecomStoreMarkup) {
    if (this.ecomStoreMarkup != null) {
      this.ecomStoreMarkup.setEcomMarkupQuaternary(null);
    }
    if (ecomStoreMarkup != null) {
      ecomStoreMarkup.setEcomMarkupQuaternary(this);
    }
    this.ecomStoreMarkup = ecomStoreMarkup;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomMarkupQuaternary)) {
      return false;
    }
    return id != null && id.equals(((EcomMarkupQuaternary) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomMarkupQuaternary{" +
            "id=" + getId() +
            ", value=" + getValue() +
            "}";
    }
}
