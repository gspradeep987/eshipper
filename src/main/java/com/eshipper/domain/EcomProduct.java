package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A EcomProduct.
 */
@Entity
@Table(name = "ecom_product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EcomProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "length")
    private Float length;

    @Column(name = "width")
    private Float width;

    @Column(name = "height")
    private Float height;

    @Column(name = "weight")
    private Float weight;

    @Size(max = 5)
    @Column(name = "dim_unit", length = 5)
    private String dimUnit;

    @Size(max = 5)
    @Column(name = "wght_unit", length = 5)
    private String wghtUnit;

    @Column(name = "goods_value")
    private Float goodsValue;

    @Column(name = "product_value")
    private Float productValue;

    @Size(max = 6)
    @Column(name = "hs_codes", length = 6)
    private String hsCodes;

    @Column(name = "sku")
    private String sku;

    @Column(name = "policy")
    private String policy;

    @Column(name = "insurance_amt")
    private Float insuranceAmt;

    @Column(name = "is_insured")
    private Boolean isInsured;

    @OneToMany(mappedBy = "ecomProduct")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EcomProductImage> ecomProductImages = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("ecomProducts")
    private Country country;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "ecom_product_ecom_warehouse",
               joinColumns = @JoinColumn(name = "ecom_product_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "ecom_warehouse_id", referencedColumnName = "id"))
    private Set<EcomWarehouse> ecomWarehouses = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("ecomProducts")
    private EcomOrder ecomOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public EcomProduct title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getLength() {
        return length;
    }

    public EcomProduct length(Float length) {
        this.length = length;
        return this;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public EcomProduct width(Float width) {
        this.width = width;
        return this;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public EcomProduct height(Float height) {
        this.height = height;
        return this;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public EcomProduct weight(Float weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getDimUnit() {
        return dimUnit;
    }

    public EcomProduct dimUnit(String dimUnit) {
        this.dimUnit = dimUnit;
        return this;
    }

    public void setDimUnit(String dimUnit) {
        this.dimUnit = dimUnit;
    }

    public String getWghtUnit() {
        return wghtUnit;
    }

    public EcomProduct wghtUnit(String wghtUnit) {
        this.wghtUnit = wghtUnit;
        return this;
    }

    public void setWghtUnit(String wghtUnit) {
        this.wghtUnit = wghtUnit;
    }

    public Float getGoodsValue() {
        return goodsValue;
    }

    public EcomProduct goodsValue(Float goodsValue) {
        this.goodsValue = goodsValue;
        return this;
    }

    public void setGoodsValue(Float goodsValue) {
        this.goodsValue = goodsValue;
    }

    public Float getProductValue() {
        return productValue;
    }

    public EcomProduct productValue(Float productValue) {
        this.productValue = productValue;
        return this;
    }

    public void setProductValue(Float productValue) {
        this.productValue = productValue;
    }

    public String getHsCodes() {
        return hsCodes;
    }

    public EcomProduct hsCodes(String hsCodes) {
        this.hsCodes = hsCodes;
        return this;
    }

    public void setHsCodes(String hsCodes) {
        this.hsCodes = hsCodes;
    }

    public String getSku() {
        return sku;
    }

    public EcomProduct sku(String sku) {
        this.sku = sku;
        return this;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPolicy() {
        return policy;
    }

    public EcomProduct policy(String policy) {
        this.policy = policy;
        return this;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public Float getInsuranceAmt() {
        return insuranceAmt;
    }

    public EcomProduct insuranceAmt(Float insuranceAmt) {
        this.insuranceAmt = insuranceAmt;
        return this;
    }

    public void setInsuranceAmt(Float insuranceAmt) {
        this.insuranceAmt = insuranceAmt;
    }

    public Boolean isIsInsured() {
        return isInsured;
    }

    public EcomProduct isInsured(Boolean isInsured) {
        this.isInsured = isInsured;
        return this;
    }

    public void setIsInsured(Boolean isInsured) {
        this.isInsured = isInsured;
    }

    public Set<EcomProductImage> getEcomProductImages() {
        return ecomProductImages;
    }

    public EcomProduct ecomProductImages(Set<EcomProductImage> ecomProductImages) {
        this.ecomProductImages = ecomProductImages;
        return this;
    }

    public EcomProduct addEcomProductImage(EcomProductImage ecomProductImage) {
        this.ecomProductImages.add(ecomProductImage);
        ecomProductImage.setEcomProduct(this);
        return this;
    }

    public EcomProduct removeEcomProductImage(EcomProductImage ecomProductImage) {
        this.ecomProductImages.remove(ecomProductImage);
        ecomProductImage.setEcomProduct(null);
        return this;
    }

    public void setEcomProductImages(Set<EcomProductImage> ecomProductImages) {
        this.ecomProductImages = ecomProductImages;
    }

    public Country getCountry() {
        return country;
    }

    public EcomProduct country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<EcomWarehouse> getEcomWarehouses() {
        return ecomWarehouses;
    }

    public EcomProduct ecomWarehouses(Set<EcomWarehouse> ecomWarehouses) {
        this.ecomWarehouses = ecomWarehouses;
        return this;
    }

    public EcomProduct addEcomWarehouse(EcomWarehouse ecomWarehouse) {
        this.ecomWarehouses.add(ecomWarehouse);
        ecomWarehouse.getEcomProducts().add(this);
        return this;
    }

    public EcomProduct removeEcomWarehouse(EcomWarehouse ecomWarehouse) {
        this.ecomWarehouses.remove(ecomWarehouse);
        ecomWarehouse.getEcomProducts().remove(this);
        return this;
    }

    public void setEcomWarehouses(Set<EcomWarehouse> ecomWarehouses) {
        this.ecomWarehouses = ecomWarehouses;
    }

    public EcomOrder getEcomOrder() {
        return ecomOrder;
    }

    public EcomProduct ecomOrder(EcomOrder ecomOrder) {
        this.ecomOrder = ecomOrder;
        return this;
    }

    public void setEcomOrder(EcomOrder ecomOrder) {
        this.ecomOrder = ecomOrder;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EcomProduct)) {
            return false;
        }
        return id != null && id.equals(((EcomProduct) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EcomProduct{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", length=" + getLength() +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            ", dimUnit='" + getDimUnit() + "'" +
            ", wghtUnit='" + getWghtUnit() + "'" +
            ", goodsValue=" + getGoodsValue() +
            ", productValue=" + getProductValue() +
            ", hsCodes='" + getHsCodes() + "'" +
            ", sku='" + getSku() + "'" +
            ", policy='" + getPolicy() + "'" +
            ", insuranceAmt=" + getInsuranceAmt() +
            ", isInsured='" + isIsInsured() + "'" +
            "}";
    }
}
