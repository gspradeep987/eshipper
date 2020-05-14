package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A WoSalesOperationalDetails.
 */
@Entity
@Table(name = "wo_sales_operational_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WoSalesOperationalDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "default_op_expense")
    private Integer defaultOpExpense;

    @Column(name = "op_exp_pallet_ship")
    private Integer opExpPalletShip;

    @Column(name = "op_exp_package_ship")
    private Integer opExpPackageShip;

    @Column(name = "op_exp_pack")
    private Integer opExpPack;

    @Column(name = "op_exp_smarte_post")
    private Integer opExpSmartePost;

    @OneToMany(mappedBy = "woSalesOperationalDetails")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WoSalesOperationalCarrier> woSalesOperationalCarriers = new HashSet<>();

    @OneToOne(mappedBy = "woSalesOperationalDetails")
    @JsonIgnore
    private WoSalesAgent woSalesAgent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDefaultOpExpense() {
        return defaultOpExpense;
    }

    public WoSalesOperationalDetails defaultOpExpense(Integer defaultOpExpense) {
        this.defaultOpExpense = defaultOpExpense;
        return this;
    }

    public void setDefaultOpExpense(Integer defaultOpExpense) {
        this.defaultOpExpense = defaultOpExpense;
    }

    public Integer getOpExpPalletShip() {
        return opExpPalletShip;
    }

    public WoSalesOperationalDetails opExpPalletShip(Integer opExpPalletShip) {
        this.opExpPalletShip = opExpPalletShip;
        return this;
    }

    public void setOpExpPalletShip(Integer opExpPalletShip) {
        this.opExpPalletShip = opExpPalletShip;
    }

    public Integer getOpExpPackageShip() {
        return opExpPackageShip;
    }

    public WoSalesOperationalDetails opExpPackageShip(Integer opExpPackageShip) {
        this.opExpPackageShip = opExpPackageShip;
        return this;
    }

    public void setOpExpPackageShip(Integer opExpPackageShip) {
        this.opExpPackageShip = opExpPackageShip;
    }

    public Integer getOpExpPack() {
        return opExpPack;
    }

    public WoSalesOperationalDetails opExpPack(Integer opExpPack) {
        this.opExpPack = opExpPack;
        return this;
    }

    public void setOpExpPack(Integer opExpPack) {
        this.opExpPack = opExpPack;
    }

    public Integer getOpExpSmartePost() {
        return opExpSmartePost;
    }

    public WoSalesOperationalDetails opExpSmartePost(Integer opExpSmartePost) {
        this.opExpSmartePost = opExpSmartePost;
        return this;
    }

    public void setOpExpSmartePost(Integer opExpSmartePost) {
        this.opExpSmartePost = opExpSmartePost;
    }

    public Set<WoSalesOperationalCarrier> getWoSalesOperationalCarriers() {
        return woSalesOperationalCarriers;
    }

    public WoSalesOperationalDetails woSalesOperationalCarriers(Set<WoSalesOperationalCarrier> woSalesOperationalCarriers) {
        this.woSalesOperationalCarriers = woSalesOperationalCarriers;
        return this;
    }

    public WoSalesOperationalDetails addWoSalesOperationalCarrier(WoSalesOperationalCarrier woSalesOperationalCarrier) {
        this.woSalesOperationalCarriers.add(woSalesOperationalCarrier);
        woSalesOperationalCarrier.setWoSalesOperationalDetails(this);
        return this;
    }

    public WoSalesOperationalDetails removeWoSalesOperationalCarrier(WoSalesOperationalCarrier woSalesOperationalCarrier) {
        this.woSalesOperationalCarriers.remove(woSalesOperationalCarrier);
        woSalesOperationalCarrier.setWoSalesOperationalDetails(null);
        return this;
    }

    public void setWoSalesOperationalCarriers(Set<WoSalesOperationalCarrier> woSalesOperationalCarriers) {
        this.woSalesOperationalCarriers = woSalesOperationalCarriers;
    }

    public WoSalesAgent getWoSalesAgent() {
        return woSalesAgent;
    }

    public WoSalesOperationalDetails woSalesAgent(WoSalesAgent woSalesAgent) {
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
        if (!(o instanceof WoSalesOperationalDetails)) {
            return false;
        }
        return id != null && id.equals(((WoSalesOperationalDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoSalesOperationalDetails{" +
            "id=" + getId() +
            ", defaultOpExpense=" + getDefaultOpExpense() +
            ", opExpPalletShip=" + getOpExpPalletShip() +
            ", opExpPackageShip=" + getOpExpPackageShip() +
            ", opExpPack=" + getOpExpPack() +
            ", opExpSmartePost=" + getOpExpSmartePost() +
            "}";
    }
}
