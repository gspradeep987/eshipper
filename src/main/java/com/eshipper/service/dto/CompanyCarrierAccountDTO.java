package com.eshipper.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.eshipper.domain.enumeration.AccountOwner;

/**
 * A DTO for the {@link com.eshipper.domain.CompanyCarrierAccount} entity.
 */
public class CompanyCarrierAccountDTO implements Serializable {
    
    private Long id;

    private AccountOwner accountOwner;

    private Integer accountNumber;

    @Size(max = 30)
    private String meterNumber;

    @Size(max = 30)
    private String key;

    @Size(max = 30)
    private String password;


    private Long regionId;

    private Long carrierId;

    private Long franchiseId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountOwner getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(AccountOwner accountOwner) {
        this.accountOwner = accountOwner;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long countryId) {
        this.regionId = countryId;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public Long getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(Long franchiseId) {
        this.franchiseId = franchiseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompanyCarrierAccountDTO companyCarrierAccountDTO = (CompanyCarrierAccountDTO) o;
        if (companyCarrierAccountDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), companyCarrierAccountDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompanyCarrierAccountDTO{" +
            "id=" + getId() +
            ", accountOwner='" + getAccountOwner() + "'" +
            ", accountNumber=" + getAccountNumber() +
            ", meterNumber='" + getMeterNumber() + "'" +
            ", key='" + getKey() + "'" +
            ", password='" + getPassword() + "'" +
            ", regionId=" + getRegionId() +
            ", carrierId=" + getCarrierId() +
            ", franchiseId=" + getFranchiseId() +
            "}";
    }
}
