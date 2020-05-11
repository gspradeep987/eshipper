package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.SalesAgentType} entity.
 */
public class SalesAgentTypeDTO implements Serializable {
    
    private Long id;

    private String agentType;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SalesAgentTypeDTO salesAgentTypeDTO = (SalesAgentTypeDTO) o;
        if (salesAgentTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), salesAgentTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SalesAgentTypeDTO{" +
            "id=" + getId() +
            ", agentType='" + getAgentType() + "'" +
            "}";
    }
}
