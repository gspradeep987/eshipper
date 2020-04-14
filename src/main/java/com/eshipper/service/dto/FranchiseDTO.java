package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.Franchise} entity.
 */
public class FranchiseDTO implements Serializable {
    
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

        FranchiseDTO franchiseDTO = (FranchiseDTO) o;
        if (franchiseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), franchiseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FranchiseDTO{" +
            "id=" + getId() +
            "}";
    }
}
