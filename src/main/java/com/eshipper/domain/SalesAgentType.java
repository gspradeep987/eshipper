package com.eshipper.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SalesAgentType.
 */
@Entity
@Table(name = "sales_agent_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesAgentType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agent_type")
    private String agentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgentType() {
        return agentType;
    }

    public SalesAgentType agentType(String agentType) {
        this.agentType = agentType;
        return this;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesAgentType)) {
            return false;
        }
        return id != null && id.equals(((SalesAgentType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesAgentType{" +
            "id=" + getId() +
            ", agentType='" + getAgentType() + "'" +
            "}";
    }
}
