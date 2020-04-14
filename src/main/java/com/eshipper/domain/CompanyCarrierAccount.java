package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.eshipper.domain.enumeration.AccountOwner;

/**
 * A CompanyCarrierAccount.
 */
@Entity
@Table(name = "company_carrier_account")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CompanyCarrierAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_owner")
    private AccountOwner accountOwner;

    @Column(name = "account_number")
    private Integer accountNumber;

    @Size(max = 30)
    @Column(name = "meter_number", length = 30)
    private String meterNumber;

    @Size(max = 30)
    @Column(name = "jhi_key", length = 30)
    private String key;

    @Size(max = 30)
    @Column(name = "password", length = 30)
    private String password;

    @ManyToOne
    @JsonIgnoreProperties("companyCarrierAccounts")
    private Country region;

    @ManyToOne
    @JsonIgnoreProperties("companyCarrierAccounts")
    private Carrier carrier;

    @ManyToOne
    @JsonIgnoreProperties("companyCarrierAccounts")
    private Franchise franchise;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountOwner getAccountOwner() {
        return accountOwner;
    }

    public CompanyCarrierAccount accountOwner(AccountOwner accountOwner) {
        this.accountOwner = accountOwner;
        return this;
    }

    public void setAccountOwner(AccountOwner accountOwner) {
        this.accountOwner = accountOwner;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public CompanyCarrierAccount accountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public CompanyCarrierAccount meterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
        return this;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getKey() {
        return key;
    }

    public CompanyCarrierAccount key(String key) {
        this.key = key;
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public CompanyCarrierAccount password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Country getRegion() {
        return region;
    }

    public CompanyCarrierAccount region(Country country) {
        this.region = country;
        return this;
    }

    public void setRegion(Country country) {
        this.region = country;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public CompanyCarrierAccount carrier(Carrier carrier) {
        this.carrier = carrier;
        return this;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public CompanyCarrierAccount franchise(Franchise franchise) {
        this.franchise = franchise;
        return this;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompanyCarrierAccount)) {
            return false;
        }
        return id != null && id.equals(((CompanyCarrierAccount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CompanyCarrierAccount{" +
            "id=" + getId() +
            ", accountOwner='" + getAccountOwner() + "'" +
            ", accountNumber=" + getAccountNumber() +
            ", meterNumber='" + getMeterNumber() + "'" +
            ", key='" + getKey() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }
}
