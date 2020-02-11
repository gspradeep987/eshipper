package com.eshipper.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.EcomMarkupPrimary} entity.
 */
public class EcomMarkupPrimaryDTO implements Serializable {

    private Long id;

    private Float value;

    @Size(max = 50)
    private String fromLane;

    @Size(max = 50)
    private String toLane;


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

    public String getFromLane() {
        return fromLane;
    }

    public void setFromLane(String fromLane) {
        this.fromLane = fromLane;
    }

    public String getToLane() {
        return toLane;
    }

    public void setToLane(String toLane) {
        this.toLane = toLane;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO = (EcomMarkupPrimaryDTO) o;
        if (ecomMarkupPrimaryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ecomMarkupPrimaryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EcomMarkupPrimaryDTO{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", fromLane='" + getFromLane() + "'" +
            ", toLane='" + getToLane() + "'" +
            "}";
    }
}
