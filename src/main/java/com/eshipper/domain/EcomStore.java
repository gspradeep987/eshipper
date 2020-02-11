package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A EcomStore.
 */
@Entity
@Table(name = "ecom_store")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EcomStore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @Size(max = 255)
    @Column(name = "nick_name", length = 255)
    private String nickName;

    @Size(max = 255)
    @Column(name = "api_password", length = 255)
    private String apiPassword;

    @Size(max = 255)
    @Column(name = "domain", length = 255)
    private String domain;

    @Column(name = "sync_frequency")
    private Integer syncFrequency;

    @Size(max = 25)
    @Column(name = "sync_interval", length = 25)
    private String syncInterval;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "is_manual_sync")
    private Boolean isManualSync;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @OneToOne
    @JoinColumn(unique = true)
    private EcomStoreAddress ecomStoreAddress;

    @OneToOne
    @JoinColumn(unique = true)
    private EcomStoreColorTheme ecomStoreColorTheme;

    @OneToOne
    @JoinColumn(unique = true)
    private EcomStoreShipmentSettings ecomStoreShipmentSettings;

    @OneToOne
    @JoinColumn(unique = true)
    private EcomStorePackageSettings ecomStorePackageSettings;

    @OneToOne
    @JoinColumn(unique = true)
    private EcomStoreMarkup ecomStoreMarkup;

    @OneToMany(mappedBy = "ecomStore")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EcomMailTemplate> ecomMailTemplates = new HashSet<>();

    @OneToMany(mappedBy = "ecomStore")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EcomOrder> ecomOrders = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("ecomStores")
    private Company company;

    @ManyToOne
    @JsonIgnoreProperties("ecomStores")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("ecomStores")
    private EcomProduct ecomProduct;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "ecom_store_shipment_service",
               joinColumns = @JoinColumn(name = "ecom_store_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "shipment_service_id", referencedColumnName = "id"))
    private Set<ShipmentService> shipmentServices = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public EcomStore name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public EcomStore nickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getApiPassword() {
        return apiPassword;
    }

    public EcomStore apiPassword(String apiPassword) {
        this.apiPassword = apiPassword;
        return this;
    }

    public void setApiPassword(String apiPassword) {
        this.apiPassword = apiPassword;
    }

    public String getDomain() {
        return domain;
    }

    public EcomStore domain(String domain) {
        this.domain = domain;
        return this;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getSyncFrequency() {
        return syncFrequency;
    }

    public EcomStore syncFrequency(Integer syncFrequency) {
        this.syncFrequency = syncFrequency;
        return this;
    }

    public void setSyncFrequency(Integer syncFrequency) {
        this.syncFrequency = syncFrequency;
    }

    public String getSyncInterval() {
        return syncInterval;
    }

    public EcomStore syncInterval(String syncInterval) {
        this.syncInterval = syncInterval;
        return this;
    }

    public void setSyncInterval(String syncInterval) {
        this.syncInterval = syncInterval;
    }

    public Boolean isActive() {
        return active;
    }

    public EcomStore active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isIsManualSync() {
        return isManualSync;
    }

    public EcomStore isManualSync(Boolean isManualSync) {
        this.isManualSync = isManualSync;
        return this;
    }

    public void setIsManualSync(Boolean isManualSync) {
        this.isManualSync = isManualSync;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public EcomStore createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public EcomStore updatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public EcomStoreAddress getEcomStoreAddress() {
        return ecomStoreAddress;
    }

    public EcomStore ecomStoreAddress(EcomStoreAddress ecomStoreAddress) {
        this.ecomStoreAddress = ecomStoreAddress;
        return this;
    }

    public void setEcomStoreAddress(EcomStoreAddress ecomStoreAddress) {
        this.ecomStoreAddress = ecomStoreAddress;
    }

    public EcomStoreColorTheme getEcomStoreColorTheme() {
        return ecomStoreColorTheme;
    }

    public EcomStore ecomStoreColorTheme(EcomStoreColorTheme ecomStoreColorTheme) {
        this.ecomStoreColorTheme = ecomStoreColorTheme;
        return this;
    }

    public void setEcomStoreColorTheme(EcomStoreColorTheme ecomStoreColorTheme) {
        this.ecomStoreColorTheme = ecomStoreColorTheme;
    }

    public EcomStoreShipmentSettings getEcomStoreShipmentSettings() {
        return ecomStoreShipmentSettings;
    }

    public EcomStore ecomStoreShipmentSettings(EcomStoreShipmentSettings ecomStoreShipmentSettings) {
        this.ecomStoreShipmentSettings = ecomStoreShipmentSettings;
        return this;
    }

    public void setEcomStoreShipmentSettings(EcomStoreShipmentSettings ecomStoreShipmentSettings) {
        this.ecomStoreShipmentSettings = ecomStoreShipmentSettings;
    }

    public EcomStorePackageSettings getEcomStorePackageSettings() {
        return ecomStorePackageSettings;
    }

    public EcomStore ecomStorePackageSettings(EcomStorePackageSettings ecomStorePackageSettings) {
        this.ecomStorePackageSettings = ecomStorePackageSettings;
        return this;
    }

    public void setEcomStorePackageSettings(EcomStorePackageSettings ecomStorePackageSettings) {
        this.ecomStorePackageSettings = ecomStorePackageSettings;
    }

    public EcomStoreMarkup getEcomStoreMarkup() {
        return ecomStoreMarkup;
    }

    public EcomStore ecomStoreMarkup(EcomStoreMarkup ecomStoreMarkup) {
        this.ecomStoreMarkup = ecomStoreMarkup;
        return this;
    }

    public void setEcomStoreMarkup(EcomStoreMarkup ecomStoreMarkup) {
        this.ecomStoreMarkup = ecomStoreMarkup;
    }

    public Set<EcomMailTemplate> getEcomMailTemplates() {
        return ecomMailTemplates;
    }

    public EcomStore ecomMailTemplates(Set<EcomMailTemplate> ecomMailTemplates) {
        this.ecomMailTemplates = ecomMailTemplates;
        return this;
    }

    public EcomStore addEcomMailTemplate(EcomMailTemplate ecomMailTemplate) {
        this.ecomMailTemplates.add(ecomMailTemplate);
        ecomMailTemplate.setEcomStore(this);
        return this;
    }

    public EcomStore removeEcomMailTemplate(EcomMailTemplate ecomMailTemplate) {
        this.ecomMailTemplates.remove(ecomMailTemplate);
        ecomMailTemplate.setEcomStore(null);
        return this;
    }

    public void setEcomMailTemplates(Set<EcomMailTemplate> ecomMailTemplates) {
        this.ecomMailTemplates = ecomMailTemplates;
    }

    public Set<EcomOrder> getEcomOrders() {
        return ecomOrders;
    }

    public EcomStore ecomOrders(Set<EcomOrder> ecomOrders) {
        this.ecomOrders = ecomOrders;
        return this;
    }

    public EcomStore addEcomOrder(EcomOrder ecomOrder) {
        this.ecomOrders.add(ecomOrder);
        ecomOrder.setEcomStore(this);
        return this;
    }

    public EcomStore removeEcomOrder(EcomOrder ecomOrder) {
        this.ecomOrders.remove(ecomOrder);
        ecomOrder.setEcomStore(null);
        return this;
    }

    public void setEcomOrders(Set<EcomOrder> ecomOrders) {
        this.ecomOrders = ecomOrders;
    }

    public Company getCompany() {
        return company;
    }

    public EcomStore company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public EcomStore user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EcomProduct getEcomProduct() {
        return ecomProduct;
    }

    public EcomStore ecomProduct(EcomProduct ecomProduct) {
        this.ecomProduct = ecomProduct;
        return this;
    }

    public void setEcomProduct(EcomProduct ecomProduct) {
        this.ecomProduct = ecomProduct;
    }

    public Set<ShipmentService> getShipmentServices() {
        return shipmentServices;
    }

    public EcomStore shipmentServices(Set<ShipmentService> shipmentServices) {
        this.shipmentServices = shipmentServices;
        return this;
    }

    public EcomStore addShipmentService(ShipmentService shipmentService) {
        this.shipmentServices.add(shipmentService);
        shipmentService.getEcomStores().add(this);
        return this;
    }

    public EcomStore removeShipmentService(ShipmentService shipmentService) {
        this.shipmentServices.remove(shipmentService);
        shipmentService.getEcomStores().remove(this);
        return this;
    }

    public void setShipmentServices(Set<ShipmentService> shipmentServices) {
        this.shipmentServices = shipmentServices;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EcomStore)) {
            return false;
        }
        return id != null && id.equals(((EcomStore) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EcomStore{" +
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
            "}";
    }
}
