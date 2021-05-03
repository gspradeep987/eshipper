package com.eshipper.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.eshipper.domain.EcomStore} entity.
 */
public class EcomStoreDTO implements Serializable {

  private Long id;

  @Size(max = 255)
  private String name;

  @Size(max = 255)
  private String nickName;

  @Size(max = 255)
  private String apiPassword;

  @Size(max = 255)
  private String domain;

  private Integer syncFrequency;

  @Size(max = 25)
  private String syncInterval;

  private Boolean active;

  private Boolean isManualSync;

  private LocalDate createdDate;

  private LocalDate updatedDate;

  private EcomStoreAddressDTO ecomStoreAddress;

  private EcomStoreColorThemeDTO ecomStoreColorTheme;

  private EcomStoreShipmentSettingsDTO ecomStoreShipmentSettings;

  private EcomStorePackageSettingsDTO ecomStorePackageSettings;

  private EcomStoreMarkupDTO ecomStoreMarkup;

  private CompanyDTO company;

  private UserDTO user;

  private EcomProductDTO ecomProduct;

  private Set<ShipmentServiceDTO> shipmentServices = new HashSet<>();

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

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getApiPassword() {
    return apiPassword;
  }

  public void setApiPassword(String apiPassword) {
    this.apiPassword = apiPassword;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public Integer getSyncFrequency() {
    return syncFrequency;
  }

  public void setSyncFrequency(Integer syncFrequency) {
    this.syncFrequency = syncFrequency;
  }

  public String getSyncInterval() {
    return syncInterval;
  }

  public void setSyncInterval(String syncInterval) {
    this.syncInterval = syncInterval;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean getIsManualSync() {
    return isManualSync;
  }

  public void setIsManualSync(Boolean isManualSync) {
    this.isManualSync = isManualSync;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDate getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(LocalDate updatedDate) {
    this.updatedDate = updatedDate;
  }

  public EcomStoreAddressDTO getEcomStoreAddress() {
    return ecomStoreAddress;
  }

  public void setEcomStoreAddress(EcomStoreAddressDTO ecomStoreAddress) {
    this.ecomStoreAddress = ecomStoreAddress;
  }

  public EcomStoreColorThemeDTO getEcomStoreColorTheme() {
    return ecomStoreColorTheme;
  }

  public void setEcomStoreColorTheme(EcomStoreColorThemeDTO ecomStoreColorTheme) {
    this.ecomStoreColorTheme = ecomStoreColorTheme;
  }

  public EcomStoreShipmentSettingsDTO getEcomStoreShipmentSettings() {
    return ecomStoreShipmentSettings;
  }

  public void setEcomStoreShipmentSettings(EcomStoreShipmentSettingsDTO ecomStoreShipmentSettings) {
    this.ecomStoreShipmentSettings = ecomStoreShipmentSettings;
  }

  public EcomStorePackageSettingsDTO getEcomStorePackageSettings() {
    return ecomStorePackageSettings;
  }

  public void setEcomStorePackageSettings(EcomStorePackageSettingsDTO ecomStorePackageSettings) {
    this.ecomStorePackageSettings = ecomStorePackageSettings;
  }

  public EcomStoreMarkupDTO getEcomStoreMarkup() {
    return ecomStoreMarkup;
  }

  public void setEcomStoreMarkup(EcomStoreMarkupDTO ecomStoreMarkup) {
    this.ecomStoreMarkup = ecomStoreMarkup;
  }

  public CompanyDTO getCompany() {
    return company;
  }

  public void setCompany(CompanyDTO company) {
    this.company = company;
  }

  public UserDTO getUser() {
    return user;
  }

  public void setUser(UserDTO user) {
    this.user = user;
  }

  public EcomProductDTO getEcomProduct() {
    return ecomProduct;
  }

  public void setEcomProduct(EcomProductDTO ecomProduct) {
    this.ecomProduct = ecomProduct;
  }

  public Set<ShipmentServiceDTO> getShipmentServices() {
    return shipmentServices;
  }

  public void setShipmentServices(Set<ShipmentServiceDTO> shipmentServices) {
    this.shipmentServices = shipmentServices;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomStoreDTO)) {
      return false;
    }

    EcomStoreDTO ecomStoreDTO = (EcomStoreDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, ecomStoreDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomStoreDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nickName='" + getNickName() + "'" +
            ", apiPassword='" + getApiPassword() + "'" +
            ", domain='" + getDomain() + "'" +
            ", syncFrequency=" + getSyncFrequency() +
            ", syncInterval='" + getSyncInterval() + "'" +
            ", active='" + getActive() + "'" +
            ", isManualSync='" + getIsManualSync() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", ecomStoreAddress=" + getEcomStoreAddress() +
            ", ecomStoreColorTheme=" + getEcomStoreColorTheme() +
            ", ecomStoreShipmentSettings=" + getEcomStoreShipmentSettings() +
            ", ecomStorePackageSettings=" + getEcomStorePackageSettings() +
            ", ecomStoreMarkup=" + getEcomStoreMarkup() +
            ", company=" + getCompany() +
            ", user=" + getUser() +
            ", ecomProduct=" + getEcomProduct() +
            ", shipmentServices=" + getShipmentServices() +
            "}";
    }
}
