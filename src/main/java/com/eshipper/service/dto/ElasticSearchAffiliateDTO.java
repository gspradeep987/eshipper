package com.eshipper.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.ElasticSearchAffiliate} entity.
 */
public class ElasticSearchAffiliateDTO implements Serializable {
    
    private Long id;


    private Long affiliateId;

    private Long elasticStatusId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(Long affiliateId) {
        this.affiliateId = affiliateId;
    }

    public Long getElasticStatusId() {
        return elasticStatusId;
    }

    public void setElasticStatusId(Long elasticStatusId) {
        this.elasticStatusId = elasticStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElasticSearchAffiliateDTO)) {
            return false;
        }

        return id != null && id.equals(((ElasticSearchAffiliateDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ElasticSearchAffiliateDTO{" +
            "id=" + getId() +
            ", affiliateId=" + getAffiliateId() +
            ", elasticStatusId=" + getElasticStatusId() +
            "}";
    }
}
