package com.eshipper.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.User10} entity.
 */
public class User10DTO implements Serializable {
    
    private Long id;


    private Long woSalesAgentId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWoSalesAgentId() {
        return woSalesAgentId;
    }

    public void setWoSalesAgentId(Long woSalesAgentId) {
        this.woSalesAgentId = woSalesAgentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User10DTO)) {
            return false;
        }

        return id != null && id.equals(((User10DTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "User10DTO{" +
            "id=" + getId() +
            ", woSalesAgentId=" + getWoSalesAgentId() +
            "}";
    }
}
