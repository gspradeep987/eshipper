package com.eshipper.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.EcomProductImage} entity.
 */
public class EcomProductImageDTO implements Serializable {

    private Long id;

    @Size(max = 100)
    private String imageName;

    @Size(max = 100)
    private String imagePath;


    private Long ecomProductId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getEcomProductId() {
        return ecomProductId;
    }

    public void setEcomProductId(Long ecomProductId) {
        this.ecomProductId = ecomProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EcomProductImageDTO ecomProductImageDTO = (EcomProductImageDTO) o;
        if (ecomProductImageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ecomProductImageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EcomProductImageDTO{" +
            "id=" + getId() +
            ", imageName='" + getImageName() + "'" +
            ", imagePath='" + getImagePath() + "'" +
            ", ecomProductId=" + getEcomProductId() +
            "}";
    }
}
