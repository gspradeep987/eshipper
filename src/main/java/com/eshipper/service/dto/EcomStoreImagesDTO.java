package com.eshipper.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.EcomStoreImages} entity.
 */
public class EcomStoreImagesDTO implements Serializable {
    
    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String imagePath;

    
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EcomStoreImagesDTO)) {
            return false;
        }

        return id != null && id.equals(((EcomStoreImagesDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EcomStoreImagesDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", imagePath='" + getImagePath() + "'" +
            "}";
    }
}
