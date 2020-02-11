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
 * A EcomOrder.
 */
@Entity
@Table(name = "ecom_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EcomOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ecom_order_number")
    private Long ecomOrderNumber;

    @Size(max = 255)
    @Column(name = "customer_name", length = 255)
    private String customerName;

    @Size(max = 255)
    @Column(name = "domain_name", length = 255)
    private String domainName;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Size(max = 255)
    @Column(name = "gateway", length = 255)
    private String gateway;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "sub_total_price")
    private Double subTotalPrice;

    @Column(name = "total_weight")
    private Float totalWeight;

    @Column(name = "total_tax")
    private Float totalTax;

    @Size(max = 100)
    @Column(name = "fulfillment_status", length = 100)
    private String fulfillmentStatus;

    @Size(max = 100)
    @Column(name = "payment_status", length = 100)
    private String paymentStatus;

    @Size(max = 100)
    @Column(name = "financial_status", length = 100)
    private String financialStatus;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Size(max = 100)
    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @Column(name = "is_cancelled")
    private Boolean isCancelled;

    @Column(name = "is_shipped")
    private Boolean isShipped;

    @Size(max = 100)
    @Column(name = "eshipper_status", length = 100)
    private String eshipperStatus;

    @Column(name = "residential_shipping_address")
    private Boolean residentialShippingAddress;

    @Column(name = "shipping_order_ref")
    private Integer shippingOrderRef;

    @Size(max = 100)
    @Column(name = "from_email", length = 100)
    private String fromEmail;

    @Column(name = "is_cancel_schedule")
    private Boolean isCancelSchedule;

    @Column(name = "is_schedule_pickup")
    private Boolean isSchedulePickup;

    @Column(name = "package_type_id")
    private Long packageTypeId;

    @OneToMany(mappedBy = "ecomOrder")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EcomProduct> ecomProducts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("ecomOrders")
    private Currency currency;

    @ManyToOne
    @JsonIgnoreProperties("ecomOrders")
    private ShippingAddress shippingAddress;

    @ManyToOne
    @JsonIgnoreProperties("ecomOrders")
    private ShippingAddress billingAddress;

    @ManyToOne
    @JsonIgnoreProperties("ecomOrders")
    private EcomStore ecomStore;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEcomOrderNumber() {
        return ecomOrderNumber;
    }

    public EcomOrder ecomOrderNumber(Long ecomOrderNumber) {
        this.ecomOrderNumber = ecomOrderNumber;
        return this;
    }

    public void setEcomOrderNumber(Long ecomOrderNumber) {
        this.ecomOrderNumber = ecomOrderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public EcomOrder customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDomainName() {
        return domainName;
    }

    public EcomOrder domainName(String domainName) {
        this.domainName = domainName;
        return this;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getName() {
        return name;
    }

    public EcomOrder name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public EcomOrder email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGateway() {
        return gateway;
    }

    public EcomOrder gateway(String gateway) {
        this.gateway = gateway;
        return this;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public EcomOrder totalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getSubTotalPrice() {
        return subTotalPrice;
    }

    public EcomOrder subTotalPrice(Double subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
        return this;
    }

    public void setSubTotalPrice(Double subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
    }

    public Float getTotalWeight() {
        return totalWeight;
    }

    public EcomOrder totalWeight(Float totalWeight) {
        this.totalWeight = totalWeight;
        return this;
    }

    public void setTotalWeight(Float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Float getTotalTax() {
        return totalTax;
    }

    public EcomOrder totalTax(Float totalTax) {
        this.totalTax = totalTax;
        return this;
    }

    public void setTotalTax(Float totalTax) {
        this.totalTax = totalTax;
    }

    public String getFulfillmentStatus() {
        return fulfillmentStatus;
    }

    public EcomOrder fulfillmentStatus(String fulfillmentStatus) {
        this.fulfillmentStatus = fulfillmentStatus;
        return this;
    }

    public void setFulfillmentStatus(String fulfillmentStatus) {
        this.fulfillmentStatus = fulfillmentStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public EcomOrder paymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getFinancialStatus() {
        return financialStatus;
    }

    public EcomOrder financialStatus(String financialStatus) {
        this.financialStatus = financialStatus;
        return this;
    }

    public void setFinancialStatus(String financialStatus) {
        this.financialStatus = financialStatus;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public EcomOrder createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public EcomOrder updatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public EcomOrder updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Boolean isIsCancelled() {
        return isCancelled;
    }

    public EcomOrder isCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
        return this;
    }

    public void setIsCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public Boolean isIsShipped() {
        return isShipped;
    }

    public EcomOrder isShipped(Boolean isShipped) {
        this.isShipped = isShipped;
        return this;
    }

    public void setIsShipped(Boolean isShipped) {
        this.isShipped = isShipped;
    }

    public String getEshipperStatus() {
        return eshipperStatus;
    }

    public EcomOrder eshipperStatus(String eshipperStatus) {
        this.eshipperStatus = eshipperStatus;
        return this;
    }

    public void setEshipperStatus(String eshipperStatus) {
        this.eshipperStatus = eshipperStatus;
    }

    public Boolean isResidentialShippingAddress() {
        return residentialShippingAddress;
    }

    public EcomOrder residentialShippingAddress(Boolean residentialShippingAddress) {
        this.residentialShippingAddress = residentialShippingAddress;
        return this;
    }

    public void setResidentialShippingAddress(Boolean residentialShippingAddress) {
        this.residentialShippingAddress = residentialShippingAddress;
    }

    public Integer getShippingOrderRef() {
        return shippingOrderRef;
    }

    public EcomOrder shippingOrderRef(Integer shippingOrderRef) {
        this.shippingOrderRef = shippingOrderRef;
        return this;
    }

    public void setShippingOrderRef(Integer shippingOrderRef) {
        this.shippingOrderRef = shippingOrderRef;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public EcomOrder fromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
        return this;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public Boolean isIsCancelSchedule() {
        return isCancelSchedule;
    }

    public EcomOrder isCancelSchedule(Boolean isCancelSchedule) {
        this.isCancelSchedule = isCancelSchedule;
        return this;
    }

    public void setIsCancelSchedule(Boolean isCancelSchedule) {
        this.isCancelSchedule = isCancelSchedule;
    }

    public Boolean isIsSchedulePickup() {
        return isSchedulePickup;
    }

    public EcomOrder isSchedulePickup(Boolean isSchedulePickup) {
        this.isSchedulePickup = isSchedulePickup;
        return this;
    }

    public void setIsSchedulePickup(Boolean isSchedulePickup) {
        this.isSchedulePickup = isSchedulePickup;
    }

    public Long getPackageTypeId() {
        return packageTypeId;
    }

    public EcomOrder packageTypeId(Long packageTypeId) {
        this.packageTypeId = packageTypeId;
        return this;
    }

    public void setPackageTypeId(Long packageTypeId) {
        this.packageTypeId = packageTypeId;
    }

    public Set<EcomProduct> getEcomProducts() {
        return ecomProducts;
    }

    public EcomOrder ecomProducts(Set<EcomProduct> ecomProducts) {
        this.ecomProducts = ecomProducts;
        return this;
    }

    public EcomOrder addEcomProduct(EcomProduct ecomProduct) {
        this.ecomProducts.add(ecomProduct);
        ecomProduct.setEcomOrder(this);
        return this;
    }

    public EcomOrder removeEcomProduct(EcomProduct ecomProduct) {
        this.ecomProducts.remove(ecomProduct);
        ecomProduct.setEcomOrder(null);
        return this;
    }

    public void setEcomProducts(Set<EcomProduct> ecomProducts) {
        this.ecomProducts = ecomProducts;
    }

    public Currency getCurrency() {
        return currency;
    }

    public EcomOrder currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public EcomOrder shippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public ShippingAddress getBillingAddress() {
        return billingAddress;
    }

    public EcomOrder billingAddress(ShippingAddress shippingAddress) {
        this.billingAddress = shippingAddress;
        return this;
    }

    public void setBillingAddress(ShippingAddress shippingAddress) {
        this.billingAddress = shippingAddress;
    }

    public EcomStore getEcomStore() {
        return ecomStore;
    }

    public EcomOrder ecomStore(EcomStore ecomStore) {
        this.ecomStore = ecomStore;
        return this;
    }

    public void setEcomStore(EcomStore ecomStore) {
        this.ecomStore = ecomStore;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EcomOrder)) {
            return false;
        }
        return id != null && id.equals(((EcomOrder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EcomOrder{" +
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
            "}";
    }
}
