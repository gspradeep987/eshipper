package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

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

  private CountryDTO country;

  private Set<EcomWarehouseDTO> ecomWarehouses = new HashSet<>();

  private EcomOrderDTO ecomOrder;

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

  public Boolean getIsInsured() {
    return isInsured;
  }

  public void setIsInsured(Boolean isInsured) {
    this.isInsured = isInsured;
  }

  public CountryDTO getCountry() {
    return country;
  }

  public void setCountry(CountryDTO country) {
    this.country = country;
  }

  public Set<EcomWarehouseDTO> getEcomWarehouses() {
    return ecomWarehouses;
  }

  public void setEcomWarehouses(Set<EcomWarehouseDTO> ecomWarehouses) {
    this.ecomWarehouses = ecomWarehouses;
  }

  public EcomOrderDTO getEcomOrder() {
    return ecomOrder;
  }

  public void setEcomOrder(EcomOrderDTO ecomOrder) {
    this.ecomOrder = ecomOrder;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomProductDTO)) {
      return false;
    }

    EcomProductDTO ecomProductDTO = (EcomProductDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, ecomProductDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
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
            ", isInsured='" + getIsInsured() + "'" +
            ", country=" + getCountry() +
            ", ecomWarehouses=" + getEcomWarehouses() +
            ", ecomOrder=" + getEcomOrder() +
            "}";
    }
}
