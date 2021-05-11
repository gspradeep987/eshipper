package com.eshipper.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.eshipper.domain.EcomOrder} entity.
 */
public class EcomOrderDTO implements Serializable {

  private Long id;

  private Long ecomOrderNumber;

  @Size(max = 255)
  private String customerName;

  @Size(max = 255)
  private String domainName;

  @Size(max = 100)
  private String name;

  @Size(max = 100)
  private String email;

  @Size(max = 255)
  private String gateway;

  private Double totalPrice;

  private Double subTotalPrice;

  private Float totalWeight;

  private Float totalTax;

  @Size(max = 100)
  private String fulfillmentStatus;

  @Size(max = 100)
  private String paymentStatus;

  @Size(max = 100)
  private String financialStatus;

  private LocalDate createdDate;

  private LocalDate updatedDate;

  @Size(max = 100)
  private String updatedBy;

  private Boolean isCancelled;

  private Boolean isShipped;

  @Size(max = 100)
  private String eshipperStatus;

  private Boolean residentialShippingAddress;

  private Integer shippingOrderRef;

  @Size(max = 100)
  private String fromEmail;

  private Boolean isCancelSchedule;

  private Boolean isSchedulePickup;

  private Long packageTypeId;

  private CurrencyDTO currency;

  private ShippingAddressDTO shippingAddress;

  private ShippingAddressDTO billingAddress;

  private EcomStoreDTO ecomStore;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getEcomOrderNumber() {
    return ecomOrderNumber;
  }

  public void setEcomOrderNumber(Long ecomOrderNumber) {
    this.ecomOrderNumber = ecomOrderNumber;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getDomainName() {
    return domainName;
  }

  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGateway() {
    return gateway;
  }

  public void setGateway(String gateway) {
    this.gateway = gateway;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Double getSubTotalPrice() {
    return subTotalPrice;
  }

  public void setSubTotalPrice(Double subTotalPrice) {
    this.subTotalPrice = subTotalPrice;
  }

  public Float getTotalWeight() {
    return totalWeight;
  }

  public void setTotalWeight(Float totalWeight) {
    this.totalWeight = totalWeight;
  }

  public Float getTotalTax() {
    return totalTax;
  }

  public void setTotalTax(Float totalTax) {
    this.totalTax = totalTax;
  }

  public String getFulfillmentStatus() {
    return fulfillmentStatus;
  }

  public void setFulfillmentStatus(String fulfillmentStatus) {
    this.fulfillmentStatus = fulfillmentStatus;
  }

  public String getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public String getFinancialStatus() {
    return financialStatus;
  }

  public void setFinancialStatus(String financialStatus) {
    this.financialStatus = financialStatus;
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

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Boolean getIsCancelled() {
    return isCancelled;
  }

  public void setIsCancelled(Boolean isCancelled) {
    this.isCancelled = isCancelled;
  }

  public Boolean getIsShipped() {
    return isShipped;
  }

  public void setIsShipped(Boolean isShipped) {
    this.isShipped = isShipped;
  }

  public String getEshipperStatus() {
    return eshipperStatus;
  }

  public void setEshipperStatus(String eshipperStatus) {
    this.eshipperStatus = eshipperStatus;
  }

  public Boolean getResidentialShippingAddress() {
    return residentialShippingAddress;
  }

  public void setResidentialShippingAddress(Boolean residentialShippingAddress) {
    this.residentialShippingAddress = residentialShippingAddress;
  }

  public Integer getShippingOrderRef() {
    return shippingOrderRef;
  }

  public void setShippingOrderRef(Integer shippingOrderRef) {
    this.shippingOrderRef = shippingOrderRef;
  }

  public String getFromEmail() {
    return fromEmail;
  }

  public void setFromEmail(String fromEmail) {
    this.fromEmail = fromEmail;
  }

  public Boolean getIsCancelSchedule() {
    return isCancelSchedule;
  }

  public void setIsCancelSchedule(Boolean isCancelSchedule) {
    this.isCancelSchedule = isCancelSchedule;
  }

  public Boolean getIsSchedulePickup() {
    return isSchedulePickup;
  }

  public void setIsSchedulePickup(Boolean isSchedulePickup) {
    this.isSchedulePickup = isSchedulePickup;
  }

  public Long getPackageTypeId() {
    return packageTypeId;
  }

  public void setPackageTypeId(Long packageTypeId) {
    this.packageTypeId = packageTypeId;
  }

  public CurrencyDTO getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyDTO currency) {
    this.currency = currency;
  }

  public ShippingAddressDTO getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(ShippingAddressDTO shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public ShippingAddressDTO getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(ShippingAddressDTO billingAddress) {
    this.billingAddress = billingAddress;
  }

  public EcomStoreDTO getEcomStore() {
    return ecomStore;
  }

  public void setEcomStore(EcomStoreDTO ecomStore) {
    this.ecomStore = ecomStore;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomOrderDTO)) {
      return false;
    }

    EcomOrderDTO ecomOrderDTO = (EcomOrderDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, ecomOrderDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomOrderDTO{" +
            "id=" + getId() +
            ", ecomOrderNumber=" + getEcomOrderNumber() +
            ", customerName='" + getCustomerName() + "'" +
            ", domainName='" + getDomainName() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", gateway='" + getGateway() + "'" +
            ", totalPrice=" + getTotalPrice() +
            ", subTotalPrice=" + getSubTotalPrice() +
            ", totalWeight=" + getTotalWeight() +
            ", totalTax=" + getTotalTax() +
            ", fulfillmentStatus='" + getFulfillmentStatus() + "'" +
            ", paymentStatus='" + getPaymentStatus() + "'" +
            ", financialStatus='" + getFinancialStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", isCancelled='" + getIsCancelled() + "'" +
            ", isShipped='" + getIsShipped() + "'" +
            ", eshipperStatus='" + getEshipperStatus() + "'" +
            ", residentialShippingAddress='" + getResidentialShippingAddress() + "'" +
            ", shippingOrderRef=" + getShippingOrderRef() +
            ", fromEmail='" + getFromEmail() + "'" +
            ", isCancelSchedule='" + getIsCancelSchedule() + "'" +
            ", isSchedulePickup='" + getIsSchedulePickup() + "'" +
            ", packageTypeId=" + getPackageTypeId() +
            ", currency=" + getCurrency() +
            ", shippingAddress=" + getShippingAddress() +
            ", billingAddress=" + getBillingAddress() +
            ", ecomStore=" + getEcomStore() +
            "}";
    }
}
