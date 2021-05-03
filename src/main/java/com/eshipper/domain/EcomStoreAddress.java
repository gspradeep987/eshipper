package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EcomStoreAddress.
 */
@Entity
@Table(name = "ecom_store_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EcomStoreAddress implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 255)
  @Column(name = "address_1", length = 255)
  private String address1;

  @Size(max = 255)
  @Column(name = "address_2", length = 255)
  private String address2;

  @Size(max = 255)
  @Column(name = "name", length = 255)
  private String name;

  @Size(max = 25)
  @Column(name = "phone", length = 25)
  private String phone;

  @Size(max = 255)
  @Column(name = "email_acc_id", length = 255)
  private String emailAccId;

  @Column(name = "default_address")
  private Boolean defaultAddress;

  @ManyToOne
  private Country country;

  @ManyToOne
  private Province province;

  @ManyToOne
  private City city;

  @JsonIgnoreProperties(
    value = {
      "ecomStoreAddress",
      "ecomStoreColorTheme",
      "ecomStoreShipmentSettings",
      "ecomStorePackageSettings",
      "ecomStoreMarkup",
      "ecomMailTemplates",
      "ecomOrders",
      "company",
      "user",
      "ecomProduct",
      "shipmentServices",
    },
    allowSetters = true
  )
  @OneToOne(mappedBy = "ecomStoreAddress")
  private EcomStore ecomStore;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EcomStoreAddress id(Long id) {
    this.id = id;
    return this;
  }

  public String getAddress1() {
    return this.address1;
  }

  public EcomStoreAddress address1(String address1) {
    this.address1 = address1;
    return this;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return this.address2;
  }

  public EcomStoreAddress address2(String address2) {
    this.address2 = address2;
    return this;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getName() {
    return this.name;
  }

  public EcomStoreAddress name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return this.phone;
  }

  public EcomStoreAddress phone(String phone) {
    this.phone = phone;
    return this;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmailAccId() {
    return this.emailAccId;
  }

  public EcomStoreAddress emailAccId(String emailAccId) {
    this.emailAccId = emailAccId;
    return this;
  }

  public void setEmailAccId(String emailAccId) {
    this.emailAccId = emailAccId;
  }

  public Boolean getDefaultAddress() {
    return this.defaultAddress;
  }

  public EcomStoreAddress defaultAddress(Boolean defaultAddress) {
    this.defaultAddress = defaultAddress;
    return this;
  }

  public void setDefaultAddress(Boolean defaultAddress) {
    this.defaultAddress = defaultAddress;
  }

  public Country getCountry() {
    return this.country;
  }

  public EcomStoreAddress country(Country country) {
    this.setCountry(country);
    return this;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public Province getProvince() {
    return this.province;
  }

  public EcomStoreAddress province(Province province) {
    this.setProvince(province);
    return this;
  }

  public void setProvince(Province province) {
    this.province = province;
  }

  public City getCity() {
    return this.city;
  }

  public EcomStoreAddress city(City city) {
    this.setCity(city);
    return this;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public EcomStore getEcomStore() {
    return this.ecomStore;
  }

  public EcomStoreAddress ecomStore(EcomStore ecomStore) {
    this.setEcomStore(ecomStore);
    return this;
  }

  public void setEcomStore(EcomStore ecomStore) {
    if (this.ecomStore != null) {
      this.ecomStore.setEcomStoreAddress(null);
    }
    if (ecomStore != null) {
      ecomStore.setEcomStoreAddress(this);
    }
    this.ecomStore = ecomStore;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomStoreAddress)) {
      return false;
    }
    return id != null && id.equals(((EcomStoreAddress) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomStoreAddress{" +
            "id=" + getId() +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", emailAccId='" + getEmailAccId() + "'" +
            ", defaultAddress='" + getDefaultAddress() + "'" +
            "}";
    }
}
