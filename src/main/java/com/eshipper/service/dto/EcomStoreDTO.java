package com.eshipper.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

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


    private Long ecomStoreAddressId;

    private Long ecomStoreColorThemeId;

    private Long ecomStoreShipmentSettingsId;

    private Long ecomStorePackageSettingsId;

    private Long ecomStoreMarkupId;

    private Long companyId;

    private Long userId;

    private Long ecomProductId;

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

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isIsManualSync() {
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

    public Long getEcomStoreAddressId() {
        return ecomStoreAddressId;
    }

    public void setEcomStoreAddressId(Long ecomStoreAddressId) {
        this.ecomStoreAddressId = ecomStoreAddressId;
    }

    public Long getEcomStoreColorThemeId() {
        return ecomStoreColorThemeId;
    }

    public void setEcomStoreColorThemeId(Long ecomStoreColorThemeId) {
        this.ecomStoreColorThemeId = ecomStoreColorThemeId;
    }

    public Long getEcomStoreShipmentSettingsId() {
        return ecomStoreShipmentSettingsId;
    }

    public void setEcomStoreShipmentSettingsId(Long ecomStoreShipmentSettingsId) {
        this.ecomStoreShipmentSettingsId = ecomStoreShipmentSettingsId;
    }

    public Long getEcomStorePackageSettingsId() {
        return ecomStorePackageSettingsId;
    }

    public void setEcomStorePackageSettingsId(Long ecomStorePackageSettingsId) {
        this.ecomStorePackageSettingsId = ecomStorePackageSettingsId;
    }

    public Long getEcomStoreMarkupId() {
        return ecomStoreMarkupId;
    }

    public void setEcomStoreMarkupId(Long ecomStoreMarkupId) {
        this.ecomStoreMarkupId = ecomStoreMarkupId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEcomProductId() {
        return ecomProductId;
    }

    public void setEcomProductId(Long ecomProductId) {
        this.ecomProductId = ecomProductId;
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EcomStoreDTO ecomStoreDTO = (EcomStoreDTO) o;
        if (ecomStoreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ecomStoreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

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
            ", active='" + isActive() + "'" +
            ", isManualSync='" + isIsManualSync() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", ecomStoreAddressId=" + getEcomStoreAddressId() +
            ", ecomStoreColorThemeId=" + getEcomStoreColorThemeId() +
            ", ecomStoreShipmentSettingsId=" + getEcomStoreShipmentSettingsId() +
            ", ecomStorePackageSettingsId=" + getEcomStorePackageSettingsId() +
            ", ecomStoreMarkupId=" + getEcomStoreMarkupId() +
            ", companyId=" + getCompanyId() +
            ", userId=" + getUserId() +
            ", ecomProductId=" + getEcomProductId() +
            "}";
    }
}
