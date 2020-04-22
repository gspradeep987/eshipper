package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A CompanyEcomSettings.
 */
@Entity
@Table(name = "company_ecom_settings")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CompanyEcomSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "notify_recipient")
    private Boolean notifyRecipient;

    @Column(name = "create_packing_list")
    private Boolean createPackingList;

    @Column(name = "create_packing_slip")
    private Boolean createPackingSlip;

    @Column(name = "ecom_module")
    private Boolean ecomModule;

    @Column(name = "include_taxes_in_rvenues")
    private Boolean includeTaxesInRvenues;

    @Column(name = "show_avg_customer_value")
    private Boolean showAvgCustomerValue;

    @Column(name = "show_avg_order_value")
    private Boolean showAvgOrderValue;

    @Column(name = "show_avg_shipment_value")
    private Boolean showAvgShipmentValue;

    @Column(name = "remove_serial_returners")
    private Boolean removeSerialReturners;

    @Column(name = "show_package_statistics")
    private Boolean showPackageStatistics;

    @Column(name = "include_all_items_ret_customers")
    private Boolean includeAllItemsRetCustomers;

    @ManyToOne
    @JsonIgnoreProperties("companyEcomSettings")
    private SignatureRequired signatureRequired;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "company_ecom_settings_carrier_settings",
               joinColumns = @JoinColumn(name = "company_ecom_settings_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "carrier_settings_id", referencedColumnName = "id"))
    private Set<CarrierSettings> carrierSettings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isNotifyRecipient() {
        return notifyRecipient;
    }

    public CompanyEcomSettings notifyRecipient(Boolean notifyRecipient) {
        this.notifyRecipient = notifyRecipient;
        return this;
    }

    public void setNotifyRecipient(Boolean notifyRecipient) {
        this.notifyRecipient = notifyRecipient;
    }

    public Boolean isCreatePackingList() {
        return createPackingList;
    }

    public CompanyEcomSettings createPackingList(Boolean createPackingList) {
        this.createPackingList = createPackingList;
        return this;
    }

    public void setCreatePackingList(Boolean createPackingList) {
        this.createPackingList = createPackingList;
    }

    public Boolean isCreatePackingSlip() {
        return createPackingSlip;
    }

    public CompanyEcomSettings createPackingSlip(Boolean createPackingSlip) {
        this.createPackingSlip = createPackingSlip;
        return this;
    }

    public void setCreatePackingSlip(Boolean createPackingSlip) {
        this.createPackingSlip = createPackingSlip;
    }

    public Boolean isEcomModule() {
        return ecomModule;
    }

    public CompanyEcomSettings ecomModule(Boolean ecomModule) {
        this.ecomModule = ecomModule;
        return this;
    }

    public void setEcomModule(Boolean ecomModule) {
        this.ecomModule = ecomModule;
    }

    public Boolean isIncludeTaxesInRvenues() {
        return includeTaxesInRvenues;
    }

    public CompanyEcomSettings includeTaxesInRvenues(Boolean includeTaxesInRvenues) {
        this.includeTaxesInRvenues = includeTaxesInRvenues;
        return this;
    }

    public void setIncludeTaxesInRvenues(Boolean includeTaxesInRvenues) {
        this.includeTaxesInRvenues = includeTaxesInRvenues;
    }

    public Boolean isShowAvgCustomerValue() {
        return showAvgCustomerValue;
    }

    public CompanyEcomSettings showAvgCustomerValue(Boolean showAvgCustomerValue) {
        this.showAvgCustomerValue = showAvgCustomerValue;
        return this;
    }

    public void setShowAvgCustomerValue(Boolean showAvgCustomerValue) {
        this.showAvgCustomerValue = showAvgCustomerValue;
    }

    public Boolean isShowAvgOrderValue() {
        return showAvgOrderValue;
    }

    public CompanyEcomSettings showAvgOrderValue(Boolean showAvgOrderValue) {
        this.showAvgOrderValue = showAvgOrderValue;
        return this;
    }

    public void setShowAvgOrderValue(Boolean showAvgOrderValue) {
        this.showAvgOrderValue = showAvgOrderValue;
    }

    public Boolean isShowAvgShipmentValue() {
        return showAvgShipmentValue;
    }

    public CompanyEcomSettings showAvgShipmentValue(Boolean showAvgShipmentValue) {
        this.showAvgShipmentValue = showAvgShipmentValue;
        return this;
    }

    public void setShowAvgShipmentValue(Boolean showAvgShipmentValue) {
        this.showAvgShipmentValue = showAvgShipmentValue;
    }

    public Boolean isRemoveSerialReturners() {
        return removeSerialReturners;
    }

    public CompanyEcomSettings removeSerialReturners(Boolean removeSerialReturners) {
        this.removeSerialReturners = removeSerialReturners;
        return this;
    }

    public void setRemoveSerialReturners(Boolean removeSerialReturners) {
        this.removeSerialReturners = removeSerialReturners;
    }

    public Boolean isShowPackageStatistics() {
        return showPackageStatistics;
    }

    public CompanyEcomSettings showPackageStatistics(Boolean showPackageStatistics) {
        this.showPackageStatistics = showPackageStatistics;
        return this;
    }

    public void setShowPackageStatistics(Boolean showPackageStatistics) {
        this.showPackageStatistics = showPackageStatistics;
    }

    public Boolean isIncludeAllItemsRetCustomers() {
        return includeAllItemsRetCustomers;
    }

    public CompanyEcomSettings includeAllItemsRetCustomers(Boolean includeAllItemsRetCustomers) {
        this.includeAllItemsRetCustomers = includeAllItemsRetCustomers;
        return this;
    }

    public void setIncludeAllItemsRetCustomers(Boolean includeAllItemsRetCustomers) {
        this.includeAllItemsRetCustomers = includeAllItemsRetCustomers;
    }

    public SignatureRequired getSignatureRequired() {
        return signatureRequired;
    }

    public CompanyEcomSettings signatureRequired(SignatureRequired signatureRequired) {
        this.signatureRequired = signatureRequired;
        return this;
    }

    public void setSignatureRequired(SignatureRequired signatureRequired) {
        this.signatureRequired = signatureRequired;
    }

    public Set<CarrierSettings> getCarrierSettings() {
        return carrierSettings;
    }

    public CompanyEcomSettings carrierSettings(Set<CarrierSettings> carrierSettings) {
        this.carrierSettings = carrierSettings;
        return this;
    }

    public CompanyEcomSettings addCarrierSettings(CarrierSettings carrierSettings) {
        this.carrierSettings.add(carrierSettings);
        carrierSettings.getCompanyEcomSettings().add(this);
        return this;
    }

    public CompanyEcomSettings removeCarrierSettings(CarrierSettings carrierSettings) {
        this.carrierSettings.remove(carrierSettings);
        carrierSettings.getCompanyEcomSettings().remove(this);
        return this;
    }

    public void setCarrierSettings(Set<CarrierSettings> carrierSettings) {
        this.carrierSettings = carrierSettings;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompanyEcomSettings)) {
            return false;
        }
        return id != null && id.equals(((CompanyEcomSettings) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CompanyEcomSettings{" +
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
            "}";
    }
}
