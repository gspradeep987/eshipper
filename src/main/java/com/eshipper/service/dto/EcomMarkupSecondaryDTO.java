package com.eshipper.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.EcomMarkupSecondary} entity.
 */
public class EcomMarkupSecondaryDTO implements Serializable {

    private Long id;

    private Float value;

    @Size(max = 50)
    private String fromZip;

    @Size(max = 50)
    private String toZip;


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

    public String getFromZip() {
        return fromZip;
    }

    public void setFromZip(String fromZip) {
        this.fromZip = fromZip;
    }

    public String getToZip() {
        return toZip;
    }

    public void setToZip(String toZip) {
        this.toZip = toZip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO = (EcomMarkupSecondaryDTO) o;
        if (ecomMarkupSecondaryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ecomMarkupSecondaryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EcomMarkupSecondaryDTO{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", fromZip='" + getFromZip() + "'" +
            ", toZip='" + getToZip() + "'" +
            "}";
    }
}
