package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ElasticSearchWoSalesAgent.
 */
@Entity
@Table(name = "elastic_search_wo_sales_agent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ElasticSearchWoSalesAgent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "elasticSearchWoSalesAgents", allowSetters = true)
    private ElasticStatus status;

    @OneToOne(mappedBy = "elasticSearchWoSalesAgent")
    @JsonIgnore
    private WoSalesAgent woSalesAgent;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ElasticStatus getStatus() {
        return status;
    }

    public ElasticSearchWoSalesAgent status(ElasticStatus elasticStatus) {
        this.status = elasticStatus;
        return this;
    }

    public void setStatus(ElasticStatus elasticStatus) {
        this.status = elasticStatus;
    }

    public WoSalesAgent getWoSalesAgent() {
        return woSalesAgent;
    }

    public ElasticSearchWoSalesAgent woSalesAgent(WoSalesAgent woSalesAgent) {
        this.woSalesAgent = woSalesAgent;
        return this;
    }

    public void setWoSalesAgent(WoSalesAgent woSalesAgent) {
        this.woSalesAgent = woSalesAgent;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElasticSearchWoSalesAgent)) {
            return false;
        }
        return id != null && id.equals(((ElasticSearchWoSalesAgent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ElasticSearchWoSalesAgent{" +
            "id=" + getId() +
            "}";
    }
}
