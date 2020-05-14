package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User10DTO user10DTO = (User10DTO) o;
        if (user10DTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), user10DTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "User10DTO{" +
            "id=" + getId() +
            ", woSalesAgentId=" + getWoSalesAgentId() +
            "}";
    }
}
