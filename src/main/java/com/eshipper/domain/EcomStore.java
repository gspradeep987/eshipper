package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EcomStore.
 */
@Entity
@Table(name = "ecom_store")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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

  @JsonIgnoreProperties(value = { "country", "province", "city", "ecomStore" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private EcomStoreAddress ecomStoreAddress;

  @JsonIgnoreProperties(value = { "ecomStore" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private EcomStoreColorTheme ecomStoreColorTheme;

  @JsonIgnoreProperties(value = { "ecomStore" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private EcomStoreShipmentSettings ecomStoreShipmentSettings;

  @JsonIgnoreProperties(value = { "ecomStore" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private EcomStorePackageSettings ecomStorePackageSettings;

  @JsonIgnoreProperties(
    value = { "ecomMarkupPrimary", "ecomMarkupSecondary", "ecomMarkupTertiary", "ecomMarkupQuaternary", "ecomStore" },
    allowSetters = true
  )
  @OneToOne
  @JoinColumn(unique = true)
  private EcomStoreMarkup ecomStoreMarkup;

  @OneToMany(mappedBy = "ecomStore")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "ecomStore" }, allowSetters = true)
  private Set<EcomMailTemplate> ecomMailTemplates = new HashSet<>();

  @OneToMany(mappedBy = "ecomStore")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "ecomProducts", "currency", "shippingAddress", "billingAddress", "ecomStore" }, allowSetters = true)
  private Set<EcomOrder> ecomOrders = new HashSet<>();

  @ManyToOne
  private Company company;

  @ManyToOne
  private User user;

  @ManyToOne
  @JsonIgnoreProperties(value = { "ecomProductImages", "country", "ecomWarehouses", "ecomOrder" }, allowSetters = true)
  private EcomProduct ecomProduct;

  @ManyToMany
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JoinTable(
    name = "rel_ecom_store__shipment_service",
    joinColumns = @JoinColumn(name = "ecom_store_id"),
    inverseJoinColumns = @JoinColumn(name = "shipment_service_id")
  )
  @JsonIgnoreProperties(value = { "ecomStores" }, allowSetters = true)
  private Set<ShipmentService> shipmentServices = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EcomStore id(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return this.name;
  }

  public EcomStore name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNickName() {
    return this.nickName;
  }

  public EcomStore nickName(String nickName) {
    this.nickName = nickName;
    return this;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getApiPassword() {
    return this.apiPassword;
  }

  public EcomStore apiPassword(String apiPassword) {
    this.apiPassword = apiPassword;
    return this;
  }

  public void setApiPassword(String apiPassword) {
    this.apiPassword = apiPassword;
  }

  public String getDomain() {
    return this.domain;
  }

  public EcomStore domain(String domain) {
    this.domain = domain;
    return this;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public Integer getSyncFrequency() {
    return this.syncFrequency;
  }

  public EcomStore syncFrequency(Integer syncFrequency) {
    this.syncFrequency = syncFrequency;
    return this;
  }

  public void setSyncFrequency(Integer syncFrequency) {
    this.syncFrequency = syncFrequency;
  }

  public String getSyncInterval() {
    return this.syncInterval;
  }

  public EcomStore syncInterval(String syncInterval) {
    this.syncInterval = syncInterval;
    return this;
  }

  public void setSyncInterval(String syncInterval) {
    this.syncInterval = syncInterval;
  }

  public Boolean getActive() {
    return this.active;
  }

  public EcomStore active(Boolean active) {
    this.active = active;
    return this;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean getIsManualSync() {
    return this.isManualSync;
  }

  public EcomStore isManualSync(Boolean isManualSync) {
    this.isManualSync = isManualSync;
    return this;
  }

  public void setIsManualSync(Boolean isManualSync) {
    this.isManualSync = isManualSync;
  }

  public LocalDate getCreatedDate() {
    return this.createdDate;
  }

  public EcomStore createdDate(LocalDate createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDate getUpdatedDate() {
    return this.updatedDate;
  }

  public EcomStore updatedDate(LocalDate updatedDate) {
    this.updatedDate = updatedDate;
    return this;
  }

  public void setUpdatedDate(LocalDate updatedDate) {
    this.updatedDate = updatedDate;
  }

  public EcomStoreAddress getEcomStoreAddress() {
    return this.ecomStoreAddress;
  }

  public EcomStore ecomStoreAddress(EcomStoreAddress ecomStoreAddress) {
    this.setEcomStoreAddress(ecomStoreAddress);
    return this;
  }

  public void setEcomStoreAddress(EcomStoreAddress ecomStoreAddress) {
    this.ecomStoreAddress = ecomStoreAddress;
  }

  public EcomStoreColorTheme getEcomStoreColorTheme() {
    return this.ecomStoreColorTheme;
  }

  public EcomStore ecomStoreColorTheme(EcomStoreColorTheme ecomStoreColorTheme) {
    this.setEcomStoreColorTheme(ecomStoreColorTheme);
    return this;
  }

  public void setEcomStoreColorTheme(EcomStoreColorTheme ecomStoreColorTheme) {
    this.ecomStoreColorTheme = ecomStoreColorTheme;
  }

  public EcomStoreShipmentSettings getEcomStoreShipmentSettings() {
    return this.ecomStoreShipmentSettings;
  }

  public EcomStore ecomStoreShipmentSettings(EcomStoreShipmentSettings ecomStoreShipmentSettings) {
    this.setEcomStoreShipmentSettings(ecomStoreShipmentSettings);
    return this;
  }

  public void setEcomStoreShipmentSettings(EcomStoreShipmentSettings ecomStoreShipmentSettings) {
    this.ecomStoreShipmentSettings = ecomStoreShipmentSettings;
  }

  public EcomStorePackageSettings getEcomStorePackageSettings() {
    return this.ecomStorePackageSettings;
  }

  public EcomStore ecomStorePackageSettings(EcomStorePackageSettings ecomStorePackageSettings) {
    this.setEcomStorePackageSettings(ecomStorePackageSettings);
    return this;
  }

  public void setEcomStorePackageSettings(EcomStorePackageSettings ecomStorePackageSettings) {
    this.ecomStorePackageSettings = ecomStorePackageSettings;
  }

  public EcomStoreMarkup getEcomStoreMarkup() {
    return this.ecomStoreMarkup;
  }

  public EcomStore ecomStoreMarkup(EcomStoreMarkup ecomStoreMarkup) {
    this.setEcomStoreMarkup(ecomStoreMarkup);
    return this;
  }

  public void setEcomStoreMarkup(EcomStoreMarkup ecomStoreMarkup) {
    this.ecomStoreMarkup = ecomStoreMarkup;
  }

  public Set<EcomMailTemplate> getEcomMailTemplates() {
    return this.ecomMailTemplates;
  }

  public EcomStore ecomMailTemplates(Set<EcomMailTemplate> ecomMailTemplates) {
    this.setEcomMailTemplates(ecomMailTemplates);
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
    if (this.ecomMailTemplates != null) {
      this.ecomMailTemplates.forEach(i -> i.setEcomStore(null));
    }
    if (ecomMailTemplates != null) {
      ecomMailTemplates.forEach(i -> i.setEcomStore(this));
    }
    this.ecomMailTemplates = ecomMailTemplates;
  }

  public Set<EcomOrder> getEcomOrders() {
    return this.ecomOrders;
  }

  public EcomStore ecomOrders(Set<EcomOrder> ecomOrders) {
    this.setEcomOrders(ecomOrders);
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
    if (this.ecomOrders != null) {
      this.ecomOrders.forEach(i -> i.setEcomStore(null));
    }
    if (ecomOrders != null) {
      ecomOrders.forEach(i -> i.setEcomStore(this));
    }
    this.ecomOrders = ecomOrders;
  }

  public Company getCompany() {
    return this.company;
  }

  public EcomStore company(Company company) {
    this.setCompany(company);
    return this;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public User getUser() {
    return this.user;
  }

  public EcomStore user(User user) {
    this.setUser(user);
    return this;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public EcomProduct getEcomProduct() {
    return this.ecomProduct;
  }

  public EcomStore ecomProduct(EcomProduct ecomProduct) {
    this.setEcomProduct(ecomProduct);
    return this;
  }

  public void setEcomProduct(EcomProduct ecomProduct) {
    this.ecomProduct = ecomProduct;
  }

  public Set<ShipmentService> getShipmentServices() {
    return this.shipmentServices;
  }

  public EcomStore shipmentServices(Set<ShipmentService> shipmentServices) {
    this.setShipmentServices(shipmentServices);
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

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

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
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
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
            ", active='" + getActive() + "'" +
            ", isManualSync='" + getIsManualSync() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            "}";
    }
}
