package com.eshipper.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.EcomProduct} entity.
 */
public class EcomProductDTO implements Serializable {

    private Long id;

    @Size(max = 100)
    private String title;

    private Float length;

    private Float width;

    private Float height;

    private Float weight;

    @Size(max = 5)
    private String dimUnit;

    @Size(max = 5)
    private String wghtUnit;

    private Float goodsValue;

    private Float productValue;

    @Size(max = 6)
    private String hsCodes;

    private String sku;

    private String policy;

    private Float insuranceAmt;

    private Boolean isInsured;


    private Long countryId;

    private Set<EcomWarehouseDTO> ecomWarehouses = new HashSet<>();

    private Long ecomOrderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getDimUnit() {
        return dimUnit;
    }

    public void setDimUnit(String dimUnit) {
        this.dimUnit = dimUnit;
    }

    public String getWghtUnit() {
        return wghtUnit;
    }

    public void setWghtUnit(String wghtUnit) {
        this.wghtUnit = wghtUnit;
    }

    public Float getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(Float goodsValue) {
        this.goodsValue = goodsValue;
    }

    public Float getProductValue() {
        return productValue;
    }

    public void setProductValue(Float productValue) {
        this.productValue = productValue;
    }

    public String getHsCodes() {
        return hsCodes;
    }

    public void setHsCodes(String hsCodes) {
        this.hsCodes = hsCodes;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public Float getInsuranceAmt() {
        return insuranceAmt;
    }

    public void setInsuranceAmt(Float insuranceAmt) {
        this.insuranceAmt = insuranceAmt;
    }

    public Boolean isIsInsured() {
        return isInsured;
    }

    public void setIsInsured(Boolean isInsured) {
        this.isInsured = isInsured;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Set<EcomWarehouseDTO> getEcomWarehouses() {
        return ecomWarehouses;
    }

    public void setEcomWarehouses(Set<EcomWarehouseDTO> ecomWarehouses) {
        this.ecomWarehouses = ecomWarehouses;
    }

    public Long getEcomOrderId() {
        return ecomOrderId;
    }

    public void setEcomOrderId(Long ecomOrderId) {
        this.ecomOrderId = ecomOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EcomProductDTO ecomProductDTO = (EcomProductDTO) o;
        if (ecomProductDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ecomProductDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EcomProductDTO{" +
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
            ", countryId=" + getCountryId() +
            ", ecomOrderId=" + getEcomOrderId() +
            "}";
    }
}
