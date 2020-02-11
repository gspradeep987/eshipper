package com.eshipper.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

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


    private Long currencyId;

    private Long shippingAddressId;

    private Long billingAddressId;

    private Long ecomStoreId;

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

    public Boolean isIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public Boolean isIsShipped() {
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

    public Boolean isResidentialShippingAddress() {
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

    public Boolean isIsCancelSchedule() {
        return isCancelSchedule;
    }

    public void setIsCancelSchedule(Boolean isCancelSchedule) {
        this.isCancelSchedule = isCancelSchedule;
    }

    public Boolean isIsSchedulePickup() {
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

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public Long getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(Long shippingAddressId) {
        this.billingAddressId = shippingAddressId;
    }

    public Long getEcomStoreId() {
        return ecomStoreId;
    }

    public void setEcomStoreId(Long ecomStoreId) {
        this.ecomStoreId = ecomStoreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EcomOrderDTO ecomOrderDTO = (EcomOrderDTO) o;
        if (ecomOrderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ecomOrderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

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
            ", isCancelled='" + isIsCancelled() + "'" +
            ", isShipped='" + isIsShipped() + "'" +
            ", eshipperStatus='" + getEshipperStatus() + "'" +
            ", residentialShippingAddress='" + isResidentialShippingAddress() + "'" +
            ", shippingOrderRef=" + getShippingOrderRef() +
            ", fromEmail='" + getFromEmail() + "'" +
            ", isCancelSchedule='" + isIsCancelSchedule() + "'" +
            ", isSchedulePickup='" + isIsSchedulePickup() + "'" +
            ", packageTypeId=" + getPackageTypeId() +
            ", currencyId=" + getCurrencyId() +
            ", shippingAddressId=" + getShippingAddressId() +
            ", billingAddressId=" + getBillingAddressId() +
            ", ecomStoreId=" + getEcomStoreId() +
            "}";
    }
}
