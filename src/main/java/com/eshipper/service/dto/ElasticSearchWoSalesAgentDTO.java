package com.eshipper.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.ElasticSearchWoSalesAgent} entity.
 */
public class ElasticSearchWoSalesAgentDTO implements Serializable {
    
    private Long id;


    private Long statusId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long elasticStatusId) {
        this.statusId = elasticStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElasticSearchWoSalesAgentDTO)) {
            return false;
        }

        return id != null && id.equals(((ElasticSearchWoSalesAgentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ElasticSearchWoSalesAgentDTO{" +
            "id=" + getId() +
            ", statusId=" + getStatusId() +
            "}";
    }
}
