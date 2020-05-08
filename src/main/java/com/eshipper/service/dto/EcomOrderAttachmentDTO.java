package com.eshipper.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.EcomOrderAttachment} entity.
 */
public class EcomOrderAttachmentDTO implements Serializable {
    
    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String attachmentPath;


    private Long ecomOrderId;
    
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

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public Long getEcomOrderId() {
        return ecomOrderId;
    }

    public void setEcomOrderId(Long ecomOrderId) {
        this.ecomOrderId = ecomOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EcomOrderAttachmentDTO ecomOrderAttachmentDTO = (EcomOrderAttachmentDTO) o;
        if (ecomOrderAttachmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ecomOrderAttachmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EcomOrderAttachmentDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", attachmentPath='" + getAttachmentPath() + "'" +
            ", ecomOrderId=" + getEcomOrderId() +
            "}";
    }
}
