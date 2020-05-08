package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.EcomOrder} entity.
 */
public class EcomOrderDTO implements Serializable {
    
    private Long id;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
            "}";
    }
}
