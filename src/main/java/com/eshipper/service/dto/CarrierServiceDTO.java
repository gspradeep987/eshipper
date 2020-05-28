package com.eshipper.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.CarrierService} entity.
 */
public class CarrierServiceDTO implements Serializable {
    
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
        if (!(o instanceof CarrierServiceDTO)) {
            return false;
        }

        return id != null && id.equals(((CarrierServiceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarrierServiceDTO{" +
            "id=" + getId() +
            "}";
    }
}
