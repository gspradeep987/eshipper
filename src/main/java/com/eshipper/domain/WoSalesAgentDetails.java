package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A WoSalesAgentDetails.
 */
@Entity
@Table(name = "wo_sales_agent_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WoSalesAgentDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hst_number")
    private Long hstNumber;

    @Column(name = "promo_code")
    private String promoCode;

    @Column(name = "promo_url")
    private String promoUrl;

    @ManyToOne
    @JsonIgnoreProperties("woSalesAgentDetails")
    private PaymentMethod paymentMethod;

    @OneToOne(mappedBy = "woSalesAgentDetails")
    @JsonIgnore
    private WoSalesAgent woSalesAgent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHstNumber() {
        return hstNumber;
    }

    public WoSalesAgentDetails hstNumber(Long hstNumber) {
        this.hstNumber = hstNumber;
        return this;
    }

    public void setHstNumber(Long hstNumber) {
        this.hstNumber = hstNumber;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public WoSalesAgentDetails promoCode(String promoCode) {
        this.promoCode = promoCode;
        return this;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getPromoUrl() {
        return promoUrl;
    }

    public WoSalesAgentDetails promoUrl(String promoUrl) {
        this.promoUrl = promoUrl;
        return this;
    }

    public void setPromoUrl(String promoUrl) {
        this.promoUrl = promoUrl;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public WoSalesAgentDetails paymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public WoSalesAgent getWoSalesAgent() {
        return woSalesAgent;
    }

    public WoSalesAgentDetails woSalesAgent(WoSalesAgent woSalesAgent) {
        this.woSalesAgent = woSalesAgent;
        return this;
    }

    public void setWoSalesAgent(WoSalesAgent woSalesAgent) {
        this.woSalesAgent = woSalesAgent;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoSalesAgentDetails)) {
            return false;
        }
        return id != null && id.equals(((WoSalesAgentDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoSalesAgentDetails{" +
            "id=" + getId() +
            ", hstNumber=" + getHstNumber() +
            ", promoCode='" + getPromoCode() + "'" +
            ", promoUrl='" + getPromoUrl() + "'" +
            "}";
    }
}
