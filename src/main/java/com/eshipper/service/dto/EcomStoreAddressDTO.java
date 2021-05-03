package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.eshipper.domain.EcomStoreAddress} entity.
 */
public class EcomStoreAddressDTO implements Serializable {

  private Long id;

  @Size(max = 255)
  private String address1;

  @Size(max = 255)
  private String address2;

  @Size(max = 255)
  private String name;

  @Size(max = 25)
  private String phone;

  @Size(max = 255)
  private String emailAccId;

  private Boolean defaultAddress;

  private CountryDTO country;

  private ProvinceDTO province;

  private CityDTO city;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmailAccId() {
    return emailAccId;
  }

  public void setEmailAccId(String emailAccId) {
    this.emailAccId = emailAccId;
  }

  public Boolean getDefaultAddress() {
    return defaultAddress;
  }

  public void setDefaultAddress(Boolean defaultAddress) {
    this.defaultAddress = defaultAddress;
  }

  public CountryDTO getCountry() {
    return country;
  }

  public void setCountry(CountryDTO country) {
    this.country = country;
  }

  public ProvinceDTO getProvince() {
    return province;
  }

  public void setProvince(ProvinceDTO province) {
    this.province = province;
  }

  public CityDTO getCity() {
    return city;
  }

  public void setCity(CityDTO city) {
    this.city = city;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomStoreAddressDTO)) {
      return false;
    }

    EcomStoreAddressDTO ecomStoreAddressDTO = (EcomStoreAddressDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, ecomStoreAddressDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomStoreAddressDTO{" +
            "id=" + getId() +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", emailAccId='" + getEmailAccId() + "'" +
            ", defaultAddress='" + getDefaultAddress() + "'" +
            ", country=" + getCountry() +
            ", province=" + getProvince() +
            ", city=" + getCity() +
            "}";
    }
}
