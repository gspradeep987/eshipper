package com.eshipper.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.EcomOrderSerchType} entity.
 */
public class EcomOrderSerchTypeDTO implements Serializable {
    
    private Long id;

    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String value;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EcomOrderSerchTypeDTO)) {
            return false;
        }

        return id != null && id.equals(((EcomOrderSerchTypeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EcomOrderSerchTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
