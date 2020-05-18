package com.eshipper.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.EcomAutomationRules} entity.
 */
public class EcomAutomationRulesDTO implements Serializable {
    
    private Long id;

    @Size(max = 255)
    private String name;

    private Boolean enable;

    private LocalDate createdDate;

    @Size(max = 255)
    private String createdBy;


    private Long ecomStoreId;
    
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

    public Boolean isEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getEcomStoreId() {
        return ecomStoreId;
    }

    public void setEcomStoreId(Long ecomStoreId) {
        this.ecomStoreId = ecomStoreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EcomAutomationRulesDTO)) {
            return false;
        }

        return id != null && id.equals(((EcomAutomationRulesDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EcomAutomationRulesDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", enable='" + isEnable() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", ecomStoreId=" + getEcomStoreId() +
            "}";
    }
}
