package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.CompanyEcomSettings} entity.
 */
public class CompanyEcomSettingsDTO implements Serializable {
    
    private Long id;

    private Boolean notifyRecipient;

    private Boolean createPackingList;

    private Boolean createPackingSlip;

    private Boolean ecomModule;

    private Boolean includeTaxesInRvenues;

    private Boolean showAvgCustomerValue;

    private Boolean showAvgOrderValue;

    private Boolean showAvgShipmentValue;

    private Boolean removeSerialReturners;

    private Boolean showPackageStatistics;

    private Boolean includeAllItemsRetCustomers;


    private Long signatureRequiredId;
    private Set<CarrierSettingsDTO> carrierSettings = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isNotifyRecipient() {
        return notifyRecipient;
    }

    public void setNotifyRecipient(Boolean notifyRecipient) {
        this.notifyRecipient = notifyRecipient;
    }

    public Boolean isCreatePackingList() {
        return createPackingList;
    }

    public void setCreatePackingList(Boolean createPackingList) {
        this.createPackingList = createPackingList;
    }

    public Boolean isCreatePackingSlip() {
        return createPackingSlip;
    }

    public void setCreatePackingSlip(Boolean createPackingSlip) {
        this.createPackingSlip = createPackingSlip;
    }

    public Boolean isEcomModule() {
        return ecomModule;
    }

    public void setEcomModule(Boolean ecomModule) {
        this.ecomModule = ecomModule;
    }

    public Boolean isIncludeTaxesInRvenues() {
        return includeTaxesInRvenues;
    }

    public void setIncludeTaxesInRvenues(Boolean includeTaxesInRvenues) {
        this.includeTaxesInRvenues = includeTaxesInRvenues;
    }

    public Boolean isShowAvgCustomerValue() {
        return showAvgCustomerValue;
    }

    public void setShowAvgCustomerValue(Boolean showAvgCustomerValue) {
        this.showAvgCustomerValue = showAvgCustomerValue;
    }

    public Boolean isShowAvgOrderValue() {
        return showAvgOrderValue;
    }

    public void setShowAvgOrderValue(Boolean showAvgOrderValue) {
        this.showAvgOrderValue = showAvgOrderValue;
    }

    public Boolean isShowAvgShipmentValue() {
        return showAvgShipmentValue;
    }

    public void setShowAvgShipmentValue(Boolean showAvgShipmentValue) {
        this.showAvgShipmentValue = showAvgShipmentValue;
    }

    public Boolean isRemoveSerialReturners() {
        return removeSerialReturners;
    }

    public void setRemoveSerialReturners(Boolean removeSerialReturners) {
        this.removeSerialReturners = removeSerialReturners;
    }

    public Boolean isShowPackageStatistics() {
        return showPackageStatistics;
    }

    public void setShowPackageStatistics(Boolean showPackageStatistics) {
        this.showPackageStatistics = showPackageStatistics;
    }

    public Boolean isIncludeAllItemsRetCustomers() {
        return includeAllItemsRetCustomers;
    }

    public void setIncludeAllItemsRetCustomers(Boolean includeAllItemsRetCustomers) {
        this.includeAllItemsRetCustomers = includeAllItemsRetCustomers;
    }

    public Long getSignatureRequiredId() {
        return signatureRequiredId;
    }

    public void setSignatureRequiredId(Long signatureRequiredId) {
        this.signatureRequiredId = signatureRequiredId;
    }

    public Set<CarrierSettingsDTO> getCarrierSettings() {
        return carrierSettings;
    }

    public void setCarrierSettings(Set<CarrierSettingsDTO> carrierSettings) {
        this.carrierSettings = carrierSettings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompanyEcomSettingsDTO companyEcomSettingsDTO = (CompanyEcomSettingsDTO) o;
        if (companyEcomSettingsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), companyEcomSettingsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompanyEcomSettingsDTO{" +
            "id=" + getId() +
            ", notifyRecipient='" + isNotifyRecipient() + "'" +
            ", createPackingList='" + isCreatePackingList() + "'" +
            ", createPackingSlip='" + isCreatePackingSlip() + "'" +
            ", ecomModule='" + isEcomModule() + "'" +
            ", includeTaxesInRvenues='" + isIncludeTaxesInRvenues() + "'" +
            ", showAvgCustomerValue='" + isShowAvgCustomerValue() + "'" +
            ", showAvgOrderValue='" + isShowAvgOrderValue() + "'" +
            ", showAvgShipmentValue='" + isShowAvgShipmentValue() + "'" +
            ", removeSerialReturners='" + isRemoveSerialReturners() + "'" +
            ", showPackageStatistics='" + isShowPackageStatistics() + "'" +
            ", includeAllItemsRetCustomers='" + isIncludeAllItemsRetCustomers() + "'" +
            ", signatureRequiredId=" + getSignatureRequiredId() +
            ", carrierSettings='" + getCarrierSettings() + "'" +
            "}";
    }
}
