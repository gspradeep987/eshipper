package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ElasticSearchAffiliate.
 */
@Entity
@Table(name = "elastic_search_affiliate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ElasticSearchAffiliate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private Affiliate affiliate;

    @ManyToOne
    @JsonIgnoreProperties(value = "elasticSearchAffiliates", allowSetters = true)
    private ElasticStatus elasticStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Affiliate getAffiliate() {
        return affiliate;
    }

    public ElasticSearchAffiliate affiliate(Affiliate affiliate) {
        this.affiliate = affiliate;
        return this;
    }

    public void setAffiliate(Affiliate affiliate) {
        this.affiliate = affiliate;
    }

    public ElasticStatus getElasticStatus() {
        return elasticStatus;
    }

    public ElasticSearchAffiliate elasticStatus(ElasticStatus elasticStatus) {
        this.elasticStatus = elasticStatus;
        return this;
    }

    public void setElasticStatus(ElasticStatus elasticStatus) {
        this.elasticStatus = elasticStatus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElasticSearchAffiliate)) {
            return false;
        }
        return id != null && id.equals(((ElasticSearchAffiliate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ElasticSearchAffiliate{" +
            "id=" + getId() +
            "}";
    }
}
