package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Affiliate.
 */
@Entity
@Table(name = "affiliate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Affiliate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "notify_user")
    private Boolean notifyUser;

    @Column(name = "promo_code")
    private String promoCode;

    @Column(name = "promo_code_url")
    private String promoCodeUrl;

    @Column(name = "commission_percentage")
    private Float commissionPercentage;

    @Column(name = "commission_date")
    private LocalDate commissionDate;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @OneToMany(mappedBy = "affiliate")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Company> affiliatedCustomers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "affiliates", allowSetters = true)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JsonIgnoreProperties(value = "affiliates", allowSetters = true)
    private Company affiliate;

    @ManyToOne
    @JsonIgnoreProperties(value = "affiliates", allowSetters = true)
    private Franchise franchise;

    @OneToOne(mappedBy = "affiliate")
    @JsonIgnore
    private ElasticSearchAffiliate elasticSearchAffiliate;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Affiliate isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean isNotifyUser() {
        return notifyUser;
    }

    public Affiliate notifyUser(Boolean notifyUser) {
        this.notifyUser = notifyUser;
        return this;
    }

    public void setNotifyUser(Boolean notifyUser) {
        this.notifyUser = notifyUser;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public Affiliate promoCode(String promoCode) {
        this.promoCode = promoCode;
        return this;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getPromoCodeUrl() {
        return promoCodeUrl;
    }

    public Affiliate promoCodeUrl(String promoCodeUrl) {
        this.promoCodeUrl = promoCodeUrl;
        return this;
    }

    public void setPromoCodeUrl(String promoCodeUrl) {
        this.promoCodeUrl = promoCodeUrl;
    }

    public Float getCommissionPercentage() {
        return commissionPercentage;
    }

    public Affiliate commissionPercentage(Float commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
        return this;
    }

    public void setCommissionPercentage(Float commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public LocalDate getCommissionDate() {
        return commissionDate;
    }

    public Affiliate commissionDate(LocalDate commissionDate) {
        this.commissionDate = commissionDate;
        return this;
    }

    public void setCommissionDate(LocalDate commissionDate) {
        this.commissionDate = commissionDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Affiliate createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public Affiliate updatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Set<Company> getAffiliatedCustomers() {
        return affiliatedCustomers;
    }

    public Affiliate affiliatedCustomers(Set<Company> companies) {
        this.affiliatedCustomers = companies;
        return this;
    }

    public Affiliate addAffiliatedCustomers(Company company) {
        this.affiliatedCustomers.add(company);
        company.setAffiliate(this);
        return this;
    }

    public Affiliate removeAffiliatedCustomers(Company company) {
        this.affiliatedCustomers.remove(company);
        company.setAffiliate(null);
        return this;
    }

    public void setAffiliatedCustomers(Set<Company> companies) {
        this.affiliatedCustomers = companies;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Affiliate paymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Company getAffiliate() {
        return affiliate;
    }

    public Affiliate affiliate(Company company) {
        this.affiliate = company;
        return this;
    }

    public void setAffiliate(Company company) {
        this.affiliate = company;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public Affiliate franchise(Franchise franchise) {
        this.franchise = franchise;
        return this;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    public ElasticSearchAffiliate getElasticSearchAffiliate() {
        return elasticSearchAffiliate;
    }

    public Affiliate elasticSearchAffiliate(ElasticSearchAffiliate elasticSearchAffiliate) {
        this.elasticSearchAffiliate = elasticSearchAffiliate;
        return this;
    }

    public void setElasticSearchAffiliate(ElasticSearchAffiliate elasticSearchAffiliate) {
        this.elasticSearchAffiliate = elasticSearchAffiliate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Affiliate)) {
            return false;
        }
        return id != null && id.equals(((Affiliate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Affiliate{" +
            "id=" + getId() +
            ", isActive='" + isIsActive() + "'" +
            ", notifyUser='" + isNotifyUser() + "'" +
            ", promoCode='" + getPromoCode() + "'" +
            ", promoCodeUrl='" + getPromoCodeUrl() + "'" +
            ", commissionPercentage=" + getCommissionPercentage() +
            ", commissionDate='" + getCommissionDate() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            "}";
    }
}
