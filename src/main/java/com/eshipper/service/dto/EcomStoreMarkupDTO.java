package com.eshipper.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.EcomStoreMarkup} entity.
 */
public class EcomStoreMarkupDTO implements Serializable {

    private Long id;

    @Size(max = 20)
    private String markupType;

    private Float minValue;

    private Float domesticValue;

    private Float intlValue;

    private Float flatRateValue;

    private Float opexValue;


    private Long ecomMarkupPrimaryId;

    private Long ecomMarkupSecondaryId;

    private Long ecomMarkupTertiaryId;

    private Long ecomMarkupQuaternaryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarkupType() {
        return markupType;
    }

    public void setMarkupType(String markupType) {
        this.markupType = markupType;
    }

    public Float getMinValue() {
        return minValue;
    }

    public void setMinValue(Float minValue) {
        this.minValue = minValue;
    }

    public Float getDomesticValue() {
        return domesticValue;
    }

    public void setDomesticValue(Float domesticValue) {
        this.domesticValue = domesticValue;
    }

    public Float getIntlValue() {
        return intlValue;
    }

    public void setIntlValue(Float intlValue) {
        this.intlValue = intlValue;
    }

    public Float getFlatRateValue() {
        return flatRateValue;
    }

    public void setFlatRateValue(Float flatRateValue) {
        this.flatRateValue = flatRateValue;
    }

    public Float getOpexValue() {
        return opexValue;
    }

    public void setOpexValue(Float opexValue) {
        this.opexValue = opexValue;
    }

    public Long getEcomMarkupPrimaryId() {
        return ecomMarkupPrimaryId;
    }

    public void setEcomMarkupPrimaryId(Long ecomMarkupPrimaryId) {
        this.ecomMarkupPrimaryId = ecomMarkupPrimaryId;
    }

    public Long getEcomMarkupSecondaryId() {
        return ecomMarkupSecondaryId;
    }

    public void setEcomMarkupSecondaryId(Long ecomMarkupSecondaryId) {
        this.ecomMarkupSecondaryId = ecomMarkupSecondaryId;
    }

    public Long getEcomMarkupTertiaryId() {
        return ecomMarkupTertiaryId;
    }

    public void setEcomMarkupTertiaryId(Long ecomMarkupTertiaryId) {
        this.ecomMarkupTertiaryId = ecomMarkupTertiaryId;
    }

    public Long getEcomMarkupQuaternaryId() {
        return ecomMarkupQuaternaryId;
    }

    public void setEcomMarkupQuaternaryId(Long ecomMarkupQuaternaryId) {
        this.ecomMarkupQuaternaryId = ecomMarkupQuaternaryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EcomStoreMarkupDTO ecomStoreMarkupDTO = (EcomStoreMarkupDTO) o;
        if (ecomStoreMarkupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ecomStoreMarkupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EcomStoreMarkupDTO{" +
            "id=" + getId() +
            ", markupType='" + getMarkupType() + "'" +
            ", minValue=" + getMinValue() +
            ", domesticValue=" + getDomesticValue() +
            ", intlValue=" + getIntlValue() +
            ", flatRateValue=" + getFlatRateValue() +
            ", opexValue=" + getOpexValue() +
            ", ecomMarkupPrimaryId=" + getEcomMarkupPrimaryId() +
            ", ecomMarkupSecondaryId=" + getEcomMarkupSecondaryId() +
            ", ecomMarkupTertiaryId=" + getEcomMarkupTertiaryId() +
            ", ecomMarkupQuaternaryId=" + getEcomMarkupQuaternaryId() +
            "}";
    }
}
