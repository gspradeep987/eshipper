package com.eshipper.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.EcomStoreColorTheme} entity.
 */
public class EcomStoreColorThemeDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String primary;

    @Size(max = 255)
    private String secondary;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EcomStoreColorThemeDTO ecomStoreColorThemeDTO = (EcomStoreColorThemeDTO) o;
        if (ecomStoreColorThemeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ecomStoreColorThemeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EcomStoreColorThemeDTO{" +
            "id=" + getId() +
            ", primary='" + getPrimary() + "'" +
            ", secondary='" + getSecondary() + "'" +
            "}";
    }
}
