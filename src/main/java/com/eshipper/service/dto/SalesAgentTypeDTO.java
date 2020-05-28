package com.eshipper.service.dto;

import java.io.Serializable;

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
        if (!(o instanceof SalesAgentTypeDTO)) {
            return false;
        }

        return id != null && id.equals(((SalesAgentTypeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesAgentTypeDTO{" +
            "id=" + getId() +
            ", agentType='" + getAgentType() + "'" +
            "}";
    }
}
