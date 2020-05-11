package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.WoSalesOperationalDetails} entity.
 */
public class WoSalesOperationalDetailsDTO implements Serializable {
    
    private Long id;

    private Integer defaultOpExpense;

    private Integer opExpPalletShip;

    private Integer opExpPackageShip;

    private Integer opExpPack;

    private Integer opExpSmartePost;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDefaultOpExpense() {
        return defaultOpExpense;
    }

    public void setDefaultOpExpense(Integer defaultOpExpense) {
        this.defaultOpExpense = defaultOpExpense;
    }

    public Integer getOpExpPalletShip() {
        return opExpPalletShip;
    }

    public void setOpExpPalletShip(Integer opExpPalletShip) {
        this.opExpPalletShip = opExpPalletShip;
    }

    public Integer getOpExpPackageShip() {
        return opExpPackageShip;
    }

    public void setOpExpPackageShip(Integer opExpPackageShip) {
        this.opExpPackageShip = opExpPackageShip;
    }

    public Integer getOpExpPack() {
        return opExpPack;
    }

    public void setOpExpPack(Integer opExpPack) {
        this.opExpPack = opExpPack;
    }

    public Integer getOpExpSmartePost() {
        return opExpSmartePost;
    }

    public void setOpExpSmartePost(Integer opExpSmartePost) {
        this.opExpSmartePost = opExpSmartePost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoSalesOperationalDetailsDTO woSalesOperationalDetailsDTO = (WoSalesOperationalDetailsDTO) o;
        if (woSalesOperationalDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woSalesOperationalDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoSalesOperationalDetailsDTO{" +
            "id=" + getId() +
            ", defaultOpExpense=" + getDefaultOpExpense() +
            ", opExpPalletShip=" + getOpExpPalletShip() +
            ", opExpPackageShip=" + getOpExpPackageShip() +
            ", opExpPack=" + getOpExpPack() +
            ", opExpSmartePost=" + getOpExpSmartePost() +
            "}";
    }
}
