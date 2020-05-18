package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A EcomAutomationRules.
 */
@Entity
@Table(name = "ecom_automation_rules")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EcomAutomationRules implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Size(max = 255)
    @Column(name = "created_by", length = 255)
    private String createdBy;

    @ManyToOne
    @JsonIgnoreProperties(value = "ecomAutomationRules", allowSetters = true)
    private EcomStore ecomStore;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public EcomAutomationRules name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isEnable() {
        return enable;
    }

    public EcomAutomationRules enable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public EcomAutomationRules createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public EcomAutomationRules createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public EcomStore getEcomStore() {
        return ecomStore;
    }

    public EcomAutomationRules ecomStore(EcomStore ecomStore) {
        this.ecomStore = ecomStore;
        return this;
    }

    public void setEcomStore(EcomStore ecomStore) {
        this.ecomStore = ecomStore;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EcomAutomationRules)) {
            return false;
        }
        return id != null && id.equals(((EcomAutomationRules) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EcomAutomationRules{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", enable='" + isEnable() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
