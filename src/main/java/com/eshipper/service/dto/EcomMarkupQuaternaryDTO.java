package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.EcomMarkupQuaternary} entity.
 */
public class EcomMarkupQuaternaryDTO implements Serializable {

    private Long id;

    private Float value;


    private Long countryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO = (EcomMarkupQuaternaryDTO) o;
        if (ecomMarkupQuaternaryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ecomMarkupQuaternaryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EcomMarkupQuaternaryDTO{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", countryId=" + getCountryId() +
            "}";
    }
}
